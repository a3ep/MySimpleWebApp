package net.bondar.web.controller;

import net.bondar.web.model.Contact;
import net.bondar.web.model.ResponseMessage;
import net.bondar.web.service.ContactService;
import net.bondar.web.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Azeral on 24.11.2015.
 */
@Controller
public class RegistrationController {

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private ContactService service;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private  UserValidator userValidator;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(Model model) {
        logger.warn("getIndex()");
//          “¿  “Œ∆≈ Õ≈ –¿¡Œ“¿≈“!
//        Contact contact1 = service.findContactByLogin("azeral");
//        Contact contact2 = service.findContactByLogin("ctumyji");
//        Contact contact3 = service.findContactByLogin("gold");
//        service.addFriendship(contact1, contact2);
//        service.addFriendship(contact1, contact3);
        return "index";
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveContact(@RequestBody @Valid Contact contact, BindingResult bindingResult){
        logger.warn("saveContact()");
        try {
            userValidator.validate(contact, bindingResult);
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
                return ResponseMessage.okMessage(service.saveContact(contact));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.errorMessage("Registration error!");
        }
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String signIn(@RequestParam(name = "login") String login, @RequestParam(name = "password")String password, Model model, HttpSession session){
        logger.warn("singIn()");
        try {
            if (login.isEmpty() || password.isEmpty()) {
                String errorString = "You should fill in both values - login and password.";
                model.addAttribute("error",errorString);
                return "index";
            }

            try {
                Contact contact = service.findContactByLogin(login);
                if(contact.getPassword().equals(password)) {
                    session.setAttribute("USER", service.findContactByLogin(login));
                }else{
                    model.addAttribute("error", "Incorrect password.");
                    return "index#author-panel";
                }

            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
                return "redirect:/home";
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/home";
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handleException(Exception e) {
        logger.debug("handlerException()");
        return ResponseMessage.errorMessage(e.getMessage());
    }
}



