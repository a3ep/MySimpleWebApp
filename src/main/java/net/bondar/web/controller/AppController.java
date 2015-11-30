package net.bondar.web.controller;

import net.bondar.web.model.Contact;
import net.bondar.web.model.ResponseMessage;
import net.bondar.web.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Azeral on 24.11.2015.
 */
@Controller
//@RequestMapping("/")
public class AppController {

    @Autowired
    private AppService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(Model model) {
        return "home";
    }

    @ResponseBody
    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ResponseMessage saveContact(@RequestBody Contact contact){
        return ResponseMessage.okMessage(service.saveContact(contact.getFirstName(), contact.getLastName(), contact.getBirthDate()));
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseMessage handleException(Exception e) {
        return ResponseMessage.errorMessage(e.getMessage());
    }
}

