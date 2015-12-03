package net.bondar.web.controller;

import net.bondar.web.model.ResponseMessage;
import net.bondar.web.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by AzeraL on 03.12.2015.
 */
@Controller
public class MainController {
    @Autowired
    private AppService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(Model model) {

        return "home";
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handleException(Exception e) {
        return ResponseMessage.errorMessage(e.getMessage());
    }
}
