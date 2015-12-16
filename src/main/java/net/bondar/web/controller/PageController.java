package net.bondar.web.controller;

import net.bondar.web.model.Contact;
import net.bondar.web.model.ContactFieldsTransport;
import net.bondar.web.model.ResponseMessage;
import net.bondar.web.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by AzeraL on 03.12.2015.
 */
@Controller
public class PageController {

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private ContactService service;


//    @Autowired
//    private HobbyService hobbyService;
//
//    @Autowired
//    private PlaceService placeService;

    @Autowired
    private MessageSource messageSource;

//        НЕ РАБОТАЕТ(((Ошибки!!!
//    @RequestMapping(value = "/test", method=RequestMethod.GET)
//    public String test(){
//        Contact contact1 = new Contact("Всеволод", "Бондарь", new Date(1990, 9, 18), "azeral", "258456", "../../resources/img/my.png");
//        Contact contact2 = new Contact("Святослав", "Бондарь", new Date(1992, 7, 24), "ctumyji", "258456", "../../resources/img/no-photo.png");
//        Contact contact3 = new Contact("Илья", "Коверя", new Date(1992, 8 , 14), "gold", "258456", "../../resources/img/no-photo.png");
//        service.saveContact(contact1);
//        service.saveContact(contact2);
//        service.saveContact(contact3);
//        service.addFriendship(contact1, contact2);
//        service.addFriendship(contact1, contact3);
//
//        Hobby hobby1 = new Hobby("Games", "Play PC games");
//        Hobby hobby2 = new Hobby("Sport", "Play football");
//        hobbyService.saveHobby(hobby1);
//        hobbyService.saveHobby(hobby2);
//
//        Place place1 = new Place("Home", "My Home", 12.0, 12.0);
//        Place place2 = new Place("Work", "My Work", 21.0, 21.0);
//        placeService.savePlace(place1);
//        placeService.savePlace(place2);
//
//        return "redirect:/index";
//    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(Model model, HttpSession httpSession) {
        logger.debug("getHome()");
        Contact user = (Contact)httpSession.getAttribute("USER");
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping(value = "/edit/profile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editProfile(@RequestBody ContactFieldsTransport contactFieldsTransport, BindingResult bindingResult, HttpSession session){
        logger.debug("editProfile()");
        try {
            Contact user = (Contact)session.getAttribute("USER");
            user.setFirstName(contactFieldsTransport.getFirstName());
            user.setLastName(contactFieldsTransport.getLastName());
            user.setBirthDate(contactFieldsTransport.getBirthDate());

            if (bindingResult.hasErrors()) {
                String errors = "";
                for (Object object : bindingResult.getAllErrors()) {
                    if(object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        errors += messageSource.getMessage(fieldError, null);
                    }
                }
                return ResponseMessage.errorMessage(errors);
            } else {
                return ResponseMessage.okMessage(service.updateContact(user));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.errorMessage("Error! Profile does't change!");
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handleException(Exception e) {
        logger.debug("handlerException()");
        return ResponseMessage.errorMessage(e.getMessage());
    }
}
