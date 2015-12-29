package net.bondar.web.controller;

import com.sun.deploy.net.HttpResponse;
import net.bondar.web.model.Contact;
import net.bondar.web.model.Hobby;
import net.bondar.web.model.Place;
import net.bondar.web.model.dto.ContactDto;
import net.bondar.web.model.ResponseMessage;
import net.bondar.web.model.dto.HobbyDto;
import net.bondar.web.model.dto.PlaceDto;
import net.bondar.web.service.ContactService;
import net.bondar.web.service.HobbyService;
import net.bondar.web.service.PlaceService;
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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    private PlaceService placeService;

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
    public ResponseMessage editProfile(@RequestBody ContactDto contactDto, HttpSession session){
        logger.debug("editProfile()");
        try {
            Contact user = (Contact)session.getAttribute("USER");
            user.setFirstName(contactDto.getFirstName());
            user.setLastName(contactDto.getLastName());
            user.setBirthDate(contactDto.getBirthDate());

            service.updateContact(user);

        } catch (Exception e) {
            logger.debug("Edit profile error!", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage(contactDto);
    }

    @RequestMapping(value = "/friends/{id}/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeFriend(@PathVariable("id") long id, HttpSession session) {
        logger.warn("removeFriend() : {}", id);

        Contact user = (Contact)session.getAttribute("USER");
        Contact friend = service.findContactById(id);
        String friendName = friend.getFirstName() +" "+ friend.getLastName();
        try {
            service.removeFriendship(user,friend);
        } catch (Exception e) {
            logger.debug("Remove friend error", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage("Success! "+ friendName + " removed from friends:)" );
    }

    @RequestMapping(value = "/saveHobby", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveHobby(@RequestBody HobbyDto hobbyDto, HttpSession session){
        logger.warn("saveHobby()");
        try {
            Hobby hobby = hobbyService.saveHobby(hobbyDto.getTitle(), hobbyDto.getDescription());
            Contact user = (Contact) session.getAttribute("USER");
            service.addHobbyToContact(user, hobby);
        } catch (Exception e) {
            logger.debug("Save hobby error!", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage("Success! New hobby saved:)");
    }

    @RequestMapping(value = "/hobbies/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editHobby(@PathVariable("id") long id, @RequestBody HobbyDto hobbyDto, HttpSession session) {
        logger.warn("editHobby() : {}", id);
        logger.warn(hobbyDto.getTitle());
        Contact user = (Contact)session.getAttribute("USER");
        Hobby hobby = hobbyService.findHobbyById(id);
        hobby.setTitle(hobbyDto.getTitle());
        hobby.setDescription(hobbyDto.getDescription());
        try {
            hobbyService.updateHobby(hobby);
            service.updateContact(user);
        } catch (Exception e) {
            logger.debug("Edit hobby error", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage(hobby);
    }

    @RequestMapping(value = "/hobbies/{id}/remove", method = RequestMethod.POST)
     @ResponseBody
     public ResponseMessage removeHobby(@PathVariable("id") long id, HttpSession session) {
        logger.warn("removeHobby() : {}", id);

        Contact user = (Contact)session.getAttribute("USER");
        Hobby hobby = hobbyService.findHobbyById(id);
        String hobbyTitle = hobby.getTitle();
        try {
            service.removeHobby(user,hobby);
            hobbyService.deleteHobby(id);
        } catch (Exception e) {
            logger.debug("Remove hobby error", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage("Success! Hobby \"" + hobbyTitle + "\" removed:)");
    }

    @RequestMapping(value = "/places/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editPlace(@PathVariable("id") long id, @RequestBody PlaceDto placeDto, HttpSession session) {
        logger.warn("editPlace() : {}", id);
        logger.warn(placeDto.getTitle());
        Contact user = (Contact)session.getAttribute("USER");
        Place place = placeService.findPlaceById(id);
        place.setTitle(placeDto.getTitle());
        place.setDescription(placeDto.getDescription());
        place.setLatitude(placeDto.getLatitude());
        place.setLongitude(placeDto.getLongitude());
        try {
            placeService.updatePlace(place);
            service.updateContact(user);
        } catch (Exception e) {
            logger.debug("Edit place error", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage(place);
    }

    @RequestMapping(value = "/places/{id}/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removePlace(@PathVariable("id") long id, HttpSession session) {
        logger.warn("removePlace{() : {}", id);

        Contact user = (Contact)session.getAttribute("USER");
        Place place = placeService.findPlaceById(id);
        String placeTitle = place.getTitle();

        try {
            service.removePlace(user, place);
            placeService.deletePlace(id);
        } catch (Exception e) {
            logger.debug("Remove place error", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage("Success! Place \"" + placeTitle + "\" removed:)");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handleException(Exception e) {
        logger.debug("handlerException()");
        return ResponseMessage.errorMessage(e.getMessage());
    }
}
