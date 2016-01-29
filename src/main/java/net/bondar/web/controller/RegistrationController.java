package net.bondar.web.controller;

import net.bondar.web.model.*;
import net.bondar.web.service.ContactService;
import net.bondar.web.service.HobbyService;
import net.bondar.web.service.PlaceService;
import net.bondar.web.service.PostService;
import net.bondar.web.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
    private UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd.MM.yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLogin(Model model) {
        logger.warn("getLogin()");
        model.addAttribute("userForm", new Contact());
        if (service.findAllContacts().isEmpty()) {
            return "redirect:/test";
        }
        return "login-authorPanel";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        logger.warn("test()");
        Contact contact1 = new Contact("Алексей", "Долматов", new Date(1979 - 1900, 8, 23), "alex", "123456", "123456");
        Contact contact2 = new Contact("Василий", "Вакуленко", new Date(1980 - 1900, 3, 20), "nagano", "123456", "123456");
        Contact contact3 = new Contact("Ирина", "Петрова", new Date(1992 - 1900, 2, 16), "irina", "123456", "123456");
        Contact contact4 = new Contact("Юлия", "Коновалова", new Date(1985 - 1900, 4, 26), "julya85", "123456", "123456");
        service.saveContact(contact1);
        service.saveContact(contact2);
        service.saveContact(contact3);
        service.saveContact(contact4);
        service.addFriendship(contact1, contact2);
        service.addFriendship(contact1, contact4);
        service.addFriendship(contact2, contact3);
        service.addFriendship(contact3, contact4);

        Hobby hobby1 = new Hobby("Music", "Play, sing or listen music");
        Hobby hobby2 = new Hobby("Sport", "Fitness");
        hobbyService.saveHobby(hobby1);
        hobbyService.saveHobby(hobby2);
        service.addHobbyToContact(contact1, hobby1);
        service.addHobbyToContact(contact2, hobby1);
        service.addHobbyToContact(contact3, hobby2);
        service.addHobbyToContact(contact4, hobby2);

        Place place1 = new Place("Home", "My Home", 12.0, 12.0);
        Place place2 = new Place("Work", "My Work", 21.0, 21.0);
        placeService.savePlace(place1);
        placeService.savePlace(place2);
        service.addPlaceToContact(contact1, place1);
        service.addPlaceToContact(contact1, place2);
        service.addPlaceToContact(contact2, place1);
        service.addPlaceToContact(contact2, place2);
        service.addPlaceToContact(contact3, place1);
        service.addPlaceToContact(contact4, place2);

        Post post1 = new Post(contact1, "Привет!", new Date());
        Post post2 = new Post(contact1, "Как дела?", new Date());
        Post post3 = new Post(contact2, "Привет!", new Date());
        Post post4 = new Post(contact2, "Как дела?", new Date());
        Post post5 = new Post(contact3, "Привет!", new Date());
        Post post6 = new Post(contact3, "Как дела?", new Date());
        Post post7 = new Post(contact4, "Привет!", new Date());
        Post post8 = new Post(contact4, "Как дела?", new Date());
        postService.savePost(post1);
        postService.savePost(post2);
        postService.savePost(post3);
        postService.savePost(post4);
        postService.savePost(post5);
        postService.savePost(post6);
        postService.savePost(post7);
        postService.savePost(post8);
        service.addPostToContact(contact1, post3);
        service.addPostToContact(contact1, post4);
        service.addPostToContact(contact2, post5);
        service.addPostToContact(contact2, post6);
        service.addPostToContact(contact3, post7);
        service.addPostToContact(contact3, post8);
        service.addPostToContact(contact4, post1);
        service.addPostToContact(contact4, post2);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public String saveContact(@ModelAttribute("userForm") @Validated Contact contact, BindingResult result, final RedirectAttributes redirectAttributes) {
        logger.warn("saveContact()");
        userValidator.validate(contact, result);
        if (result.hasErrors()) {
            return "login";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Congratulations " + contact.getFirstName() + ", registration successful!:)");
        }
        Contact newContact = new Contact(contact.getFirstName(), contact.getLastName(), contact.getBirthDate(), contact.getUserName(), contact.getPassword(), contact.getConfirmPassword());
        service.saveContact(newContact);
        return "redirect:/";
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String signIn(@ModelAttribute("userForm") Contact contact, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        logger.warn("singIn()");
        Contact contactInDB = service.findContactByUserName(contact.getUserName());
        if (contactInDB == null || !(contact.getPassword().equals(contactInDB.getPassword()))) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Wrong login or password, please try again.");
            return "redirect:/";
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
        logger.debug("handlerException()", e);
        return ResponseMessage.errorMessage(e.getMessage());
    }
}




