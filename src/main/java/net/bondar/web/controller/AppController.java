package net.bondar.web.controller;

import net.bondar.web.model.Contact;
import net.bondar.web.model.ResponseMessage;
import net.bondar.web.model.dto.ContactDto;
import net.bondar.web.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Azeral on 24.11.2015.
 */
@Controller
public class AppController{

    @Autowired
    private AppService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView doHome() {
        return new ModelAndView("home");
    }


    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveContact(@RequestBody ContactDto contactDto){
        return ResponseMessage.okMessage(service.saveContact(contactDto.getFirstName(), contactDto.getLastName(), contactDto.getBirthDate()));
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handleException(Exception e) {
        return ResponseMessage.errorMessage(e.getMessage());
    }
}



