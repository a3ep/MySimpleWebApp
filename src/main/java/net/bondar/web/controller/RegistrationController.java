package net.bondar.web.controller;

import net.bondar.web.model.Contact;
import net.bondar.web.model.ResponseMessage;
import net.bondar.web.service.ContactService;
import net.bondar.web.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Azeral on 24.11.2015.
 */
@Controller
public class RegistrationController extends HttpServlet {

    @Autowired
    private ContactService service;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private  UserValidator userValidator;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getHome(Model model) {
        return "index";
    }


    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveContact(@RequestBody @Valid Contact contact, BindingResult bindingResult){
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login").toLowerCase();
        String password = request.getParameter("password");


        if (login.isEmpty() || password.isEmpty()) {
            String errorString = "You should fill in both values - login and password.";
            request.setAttribute("Error", errorString);
            request.getRequestDispatcher("index").forward(request, response);
        }

        try {
            service.findContactByLogin(login);
            HttpSession session = request.getSession(true);
            session.setAttribute("USER", login);
            request.getRequestDispatcher("home").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("Error", e.getMessage());
            request.getRequestDispatcher("index").forward(request, response);
        }
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handleException(Exception e) {
        return ResponseMessage.errorMessage(e.getMessage());
    }
}



