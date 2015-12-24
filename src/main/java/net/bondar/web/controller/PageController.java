package net.bondar.web.controller;

import net.bondar.web.model.Contact;
import net.bondar.web.model.Hobby;
import net.bondar.web.model.dto.ContactDto;
import net.bondar.web.model.ResponseMessage;
import net.bondar.web.model.dto.HobbyDto;
import net.bondar.web.service.ContactService;
import net.bondar.web.service.HobbyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AzeraL on 03.12.2015.
 */
@Controller
public class PageController {

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private ContactService service;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd.MM.yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(Model model, HttpSession httpSession) {
        logger.debug("getHome()");
        Contact user = (Contact)httpSession.getAttribute("USER");
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping(value = "/edit/profile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editProfile(@RequestBody ContactDto contactDto, BindingResult bindingResult, HttpSession session, final RedirectAttributes redirectAttributes){
        logger.debug("editProfile()");
        try {
            Contact user = (Contact)session.getAttribute("USER");
            user.setFirstName(contactDto.getFirstName());
            user.setLastName(contactDto.getLastName());
            user.setBirthDate(contactDto.getBirthDate());

            service.updateContact(user);

            if (bindingResult.hasErrors()) {
                String errors = "";
                for (Object object : bindingResult.getAllErrors()) {
                    if(object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        errors += messageSource.getMessage(fieldError, null);
                    }
                }
                redirectAttributes.addFlashAttribute("css", "danger");
                redirectAttributes.addFlashAttribute("msg", "Error! Profile does't change!");
                return ResponseMessage.errorMessage(errors);
            } else {
                redirectAttributes.addFlashAttribute("css", "success");
                redirectAttributes.addFlashAttribute("msg", "Done!");
                return ResponseMessage.okMessage(contactDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.errorMessage("Error! Profile does't change!");
        }
    }

    @RequestMapping(value = "/saveHobby", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveHobby(@RequestBody HobbyDto hobbyDto, HttpSession session, BindingResult bindingResult, Model model){
        logger.warn("saveHobby()");
        try {
            Hobby hobby = hobbyService.saveHobby(hobbyDto.getTitle(), hobbyDto.getDescription());
            Contact user = (Contact) session.getAttribute("USER");
            service.addHobbyToContact(user, hobby);

            if (bindingResult.hasErrors()) {
                String errors = "";
                for (Object object : bindingResult.getAllErrors()) {
                    if (object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;
                        errors += messageSource.getMessage(fieldError, null);
                    }
                }
                return ResponseMessage.errorMessage(errors);
            }else{
                model.addAttribute("USER", user);
                return ResponseMessage.okMessage(user);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.errorMessage("Error! Hobby does't save!");
        }
    }

    @RequestMapping(value = "/friends/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") long id, Model model,HttpSession session, final RedirectAttributes redirectAttributes) {
        logger.warn("deleteUser() : {}", id);

        Contact user = (Contact)session.getAttribute("USER");
        Contact friend = service.findContactById(id);
        System.out.println(user.getFriendList());
        try {
            service.removeFriendship(user,friend);
        } catch (Exception e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Friend was removed!");
        model.addAttribute("user", user);
        return "redirect:/home#tab_friends-panel";

    }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handleException(Exception e) {
        logger.debug("handlerException()");
        return ResponseMessage.errorMessage(e.getMessage());
    }
}
