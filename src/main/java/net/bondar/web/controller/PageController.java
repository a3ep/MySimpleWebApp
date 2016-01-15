package net.bondar.web.controller;

import net.bondar.web.model.*;
import net.bondar.web.model.dto.ContactDto;
import net.bondar.web.model.dto.HobbyDto;
import net.bondar.web.model.dto.PlaceDto;
import net.bondar.web.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PostService postService;

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

        Contact user = (Contact) httpSession.getAttribute("USER");
        model.addAttribute("user", user);

        Set<Contact> contacts = service.findAllContacts();
        contacts.remove(user);
        logger.debug(contacts.toString());
        model.addAttribute("contacts", contacts);
        return "home";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(Model model, HttpSession session) {
        logger.debug("logout()");
        session.invalidate();
        model.addAttribute("userForm", new Contact());
        return "redirect:/login";
    }


    @RequestMapping(value = "/edit/profile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editProfile(@RequestBody ContactDto contactDto, HttpSession session) {
        logger.debug("editProfile()");
        try {
            Contact user = (Contact) session.getAttribute("USER");
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

    @RequestMapping(value = "/friends/{id}/invokeMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage invokeMessage(@PathVariable("id") long id, HttpSession session) {
        logger.warn("invokeMessage()");

        Contact user = (Contact) session.getAttribute("USER");
        service.refreshContact(user);
        Contact friend = service.findContactById(id);
        logger.debug(friend.getId() + " " + friend.getFirstName() + " " + friend.getLastName());
        List<Message> messages = new ArrayList<>();
        try {
            if(user.getConversation().isEmpty()){
                Chat newChat = new Chat(friend);
                chatService.saveChat(newChat);
                service.addChatToContact(user, newChat);
            }
            for (Chat chat : user.getConversation()) {
                if (chat.getUserTo().equals(friend)) {
                    if(chat.getChatMessages().isEmpty()){
                        break;
                    }
                    for (Message message : chat.getChatMessages()) {
                        messages.add(message);
                    }
                }
            }
        } catch (Exception e) {
            return ResponseMessage.errorMessage(e.getMessage());
        }
        ContactDto friendDto = new ContactDto();
        friendDto.setId(friend.getId());
        friendDto.setFirstName(friend.getFirstName());
        friendDto.setLastName(friend.getLastName());
        logger.debug(friendDto.toString());
        return ResponseMessage.okMessage(friendDto, messages);
    }

    @RequestMapping(value = "/friends/{id}/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage sendMessage(@RequestBody String message, @PathVariable("id") long id, HttpSession session) {
        logger.warn("sendMessage()");

        Contact user = (Contact) session.getAttribute("USER");
        service.refreshContact(user);
        Contact friend = service.findContactById(id);
        Message newMessage = new Message(user, new Date(), message);
        List<Message> messages = new ArrayList<>();
        try {
            messageService.saveMessage(newMessage);
            for (Chat chat : user.getConversation()) {
                if (chat.getUserTo().equals(friend)) {
                    Chat currentChat = chat;
                    currentChat.getChatMessages().add(newMessage);
                    chatService.updateChat(currentChat);
                    for (Message m : currentChat.getChatMessages()) {
                        messages.add(m);
                    }
                }
            }
            service.updateContact(user);
        } catch (Exception e) {
            return ResponseMessage.errorMessage(e.getMessage());
        }
        ContactDto friendDto = new ContactDto();
        friendDto.setId(friend.getId());
        friendDto.setFirstName(friend.getFirstName());
        friendDto.setLastName(friend.getLastName());
        return ResponseMessage.okMessage(friendDto, messages);
    }

    @RequestMapping(value = "/friends/{id}/invokePost", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage invokePost(@PathVariable("id") long id, HttpSession session) {
        logger.warn("invokePost()");

        Contact friend = service.findContactById(id);
        ContactDto friendDto = new ContactDto();
        friendDto.setId(friend.getId());
        friendDto.setFirstName(friend.getFirstName());
        friendDto.setLastName(friend.getLastName());
        return ResponseMessage.okMessage(friendDto);
    }

    @RequestMapping(value = "/friends/{id}/sendPost", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage sendPost(@RequestBody String message, @PathVariable("id") long id, HttpSession session) {
        logger.warn("sendPost()");

        Contact user = (Contact) session.getAttribute("USER");
        service.refreshContact(user);
        Contact friend = service.findContactById(id);
        Post newPost = new Post(user, message, new Date());
        try {
            service.addPostToContact(friend, postService.savePost(newPost));
        } catch (Exception e) {
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage("");
    }

    @RequestMapping(value = "/friends/{id}/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addFriend(@PathVariable("id") long id, HttpSession session) {
        logger.warn("addFriend() : {}", id);

        Contact user = (Contact) session.getAttribute("USER");
        service.refreshContact(user);
        Contact friend = service.findContactById(id);
        try {
            service.addFriendship(user, friend);
            service.updateContact(user);
        } catch (Exception e) {
            logger.debug("Add friend error", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        session.setAttribute("USER", user);
        ContactDto newFriend = new ContactDto();
        newFriend.setId(friend.getId());
        newFriend.setFirstName(friend.getFirstName());
        newFriend.setLastName(friend.getLastName());
        return ResponseMessage.okMessage(newFriend);
    }

    @RequestMapping(value = "/friends/{id}/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeFriend(@PathVariable("id") long id, HttpSession session) {
        logger.warn("removeFriend() : {}", id);

        Contact user = (Contact) session.getAttribute("USER");
        Contact friend = service.findContactById(id);
        String friendName = friend.getFirstName() + " " + friend.getLastName();
        try {
            service.removeFriendship(user, friend);
        } catch (Exception e) {
            logger.debug("Remove friend error", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage("Success! " + friendName + " removed from friends:)");
    }

    @RequestMapping(value = "/saveHobby", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveHobby(@RequestBody HobbyDto hobbyDto, HttpSession session) {
        logger.warn("saveHobby()");
        try {
            Hobby hobby = hobbyService.saveHobby(hobbyDto.getTitle(), hobbyDto.getDescription());
            Contact user = (Contact) session.getAttribute("USER");
            service.refreshContact(user);
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
        Contact user = (Contact) session.getAttribute("USER");
        logger.info("User " + user.getFirstName() + " tries to edit hobby with id:" + id);
        Hobby hobby = hobbyService.findHobbyById(id);
        hobby.setTitle(hobbyDto.getTitle());
        hobby.setDescription(hobbyDto.getDescription());
        try {
            hobbyService.updateHobby(hobby);
            service.refreshContact(user);
            service.updateContact(user);
            session.setAttribute("USER", user);
        } catch (Exception e) {
            logger.debug("Edit hobby error", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage(hobbyService.findHobbyById(id));
    }

    @RequestMapping(value = "/hobbies/{id}/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeHobby(@PathVariable("id") long id, HttpSession session) {
        logger.warn("removeHobby() : {}", id);

        Contact user = (Contact) session.getAttribute("USER");
        Hobby hobby = hobbyService.findHobbyById(id);
        String hobbyTitle = hobby.getTitle();
        try {
            service.removeHobby(user, hobby);
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

        Contact user = (Contact) session.getAttribute("USER");
        Place place = placeService.findPlaceById(id);
        place.setTitle(placeDto.getTitle());
        place.setDescription(placeDto.getDescription());
        place.setLatitude(placeDto.getLatitude());
        place.setLongitude(placeDto.getLongitude());
        try {
            placeService.updatePlace(place);
            service.refreshContact(user);
            service.updateContact(user);
            session.setAttribute("USER", user);
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

        Contact user = (Contact) session.getAttribute("USER");
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

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResponseMessage handleException(Exception e) {
//        logger.debug("handlerException()");
//        return ResponseMessage.errorMessage(e.getMessage());
//    }
}
