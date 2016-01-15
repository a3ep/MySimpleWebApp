package net.bondar.web.controller;

import net.bondar.web.model.*;
import net.bondar.web.service.*;
import net.bondar.web.validator.UserSingInValidator;
import net.bondar.web.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Azeral on 24.11.2015.
 */
@Controller
public class RegistrationController {

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private ContactService service;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PostService postService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserSingInValidator userSingInValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd.MM.yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model) {
        logger.warn("getLogin()");
        model.addAttribute("userForm", new Contact());
        if(service.findAllContacts().isEmpty()){
            return "redirect:/test";
        }
        return "login-authorPanel";
    }

    @RequestMapping(value = "/test", method=RequestMethod.GET)
    public String test(){
        logger.warn("test()");
        Contact contact1 = new Contact("Всеволод", "Бондарь", new Date(1990-1900, 9, 18), "azeral", "258456", "258456", "resources/img/my.png");
        Contact contact2 = new Contact("Святослав", "Бондарь", new Date(1992-1900, 7, 24), "ctumyji", "258456", "258456", "resources/img/slavik.jpg");
        Contact contact3 = new Contact("Илья", "Коверя", new Date(1992-1900, 8 , 14), "goldLady", "258456", "258456", "resources/img/illia.jpg");
        Contact contact4 = new Contact("Иван", "Иванов", new Date(1900-1900, 4, 13), "azazaz", "258456", "258456", "resources/img/no-photo.png");
        service.saveContact(contact1);
        service.saveContact(contact2);
        service.saveContact(contact3);
        service.saveContact(contact4);
        service.addFriendship(contact1, contact2);
        service.addFriendship(contact1, contact3);

        Hobby hobby1 = new Hobby("Games", "Play PC games");
        Hobby hobby2 = new Hobby("Sport", "Play football");
        hobbyService.saveHobby(hobby1);
        hobbyService.saveHobby(hobby2);
        service.addHobbyToContact(contact1, hobby1);
        service.addHobbyToContact(contact1, hobby2);

        Place place1 = new Place("Home", "My Home", 12.0, 12.0);
        Place place2 = new Place("Work", "My Work", 21.0, 21.0);
        placeService.savePlace(place1);
        placeService.savePlace(place2);
        service.addPlaceToContact(contact1, place1);
        service.addPlaceToContact(contact1, place2);

//                     Ошибка SQL!!!!!
//        Post post1 = new Post(contact2, "Привет!", new Date());
//        postService.savePost(post1);
//        service.addPostToContact(contact1, post1);

//                        Ошибка!!!
//        Chat chat1 = new Chat(contact2);
//        Message message1 = new Message(contact2, new Date(), "Привет!");
//        Message message2 = new Message(contact1, new Date(), "Azaz");
//        messageService.saveMessage(message1);
//        messageService.saveMessage(message2);
//
//        chat1.getChatMessages().add(message1);
//        chat1.getChatMessages().add(message2);
//        chatService.saveChat(chat1);
//        service.addChatToContact(contact1, chat1);
        return "redirect:/login";
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public String saveContact(@ModelAttribute("userForm") @Validated Contact contact, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        logger.warn("saveContact()");
        userValidator.validate(contact, result);
        if (result.hasErrors()) {
            return "login";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Congratulations " + contact.getFirstName() + ", registration successful!:)");
        }

        service.saveContact(contact);

        return "redirect:/login#tab_author-panel";
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String signIn(@ModelAttribute("userForm") @Validated Contact contact, BindingResult result, Model model, HttpSession session) {
        logger.warn("singIn()");
        userSingInValidator.validate(contact, result);
        if (result.hasErrors()) {
            return "login-authorPanel";
        }

        try {
            session.setAttribute("USER", service.findContactByUserName(contact.getUserName()));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/home";
        }
        return "redirect:/home";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handleException(Exception e) {
        logger.debug("handlerException()",e);
        return ResponseMessage.errorMessage(e.getMessage());
    }

    //    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseMessage saveContact(@RequestBody @Valid Contact contact, BindingResult bindingResult) {
//        logger.warn("saveContact()");
//        try {
//            userValidator.validate(contact, bindingResult);
//            if (bindingResult.hasErrors()) {
//                String errors = "";
//                for (Object object : bindingResult.getAllErrors()) {
//                    if (object instanceof FieldError) {
//                        FieldError fieldError = (FieldError) object;
//                        errors += messageSource.getMessage(fieldError, null);
//                    }
//                }
//                return ResponseMessage.errorMessage(errors);
//            } else {
//                return ResponseMessage.okMessage(service.saveContact(contact));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseMessage.errorMessage("Registration error!");
//        }
//    }

//    @RequestMapping(value = "/author", method = RequestMethod.GET)
//    public String signIn(@RequestParam(name = "userName") String userName, @RequestParam(name = "userPassword") String password, Model model, HttpSession session) {
//        logger.warn("singIn()");
//        try {
//            if (userName.isEmpty() || password.isEmpty()) {
//                String errorString = "You should fill in both values - login and password.";
//                model.addAttribute("error", errorString);
//                return "login";
//            }
//
//            try {
//                Contact contact = service.findContactByUserName(userName);
//                if (contact.getPassword().equals(password)) {
//                    session.setAttribute("USER", service.findContactByUserName(userName));
//                } else {
//                    model.addAttribute("error", "Incorrect password.");
//                    return "redirect:/login#tab_author-panel";
//                }
//
//            } catch (Exception e) {
//                model.addAttribute("error", e.getMessage());
//                return "redirect:/home";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:/home";
//    }

}




