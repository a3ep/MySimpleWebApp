package net.bondar.web.controller;

import net.bondar.web.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Azeral on 24.11.2015.
 */
@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    private AppService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(Model model) {
        return "home";
    }

}
