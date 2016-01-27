package net.bondar.web.controller;

import net.bondar.web.model.*;
import net.bondar.web.model.dto.*;
import net.bondar.web.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
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

    @Qualifier("viewResolver")
    @Autowired
    ViewResolver resolver;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd.MM.yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
    }
//-----------------------------------------------------------------
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(Model model, HttpSession session) {
        logger.debug("getHome()");

        Contact user = getPersistUser(session);
        if (user.getFirstName().equals("emptySession")){
            return "redirect:errorPage";
        }
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping(value = "/errorPage", method = RequestMethod.GET)
    public String getErrorPage() {
        logger.debug("getErrorPage()");

        return "errorPage";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        logger.debug("logout()");
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value = "/edit/profile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editProfile(@RequestBody ContactDto contactDto, HttpSession session) {
        logger.debug("editProfile()");
        try {
            Contact user = getPersistUser(session);
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
       public ResponseMessage invokeMessage(@PathVariable("id") long id, HttpSession session, Model model, HttpServletRequest req) {
        logger.warn("invokeMessage()");

        Contact user = getPersistUser(session);
        Contact friend = service.findContactById(id);
        logger.debug(friend.getId() + " " + friend.getFirstName() + " " + friend.getLastName());
        List<Message> messages = new ArrayList<>();
        try {
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
            logger.debug("Invoke message error!", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        model.addAttribute("messages", messages);
        String result = null;
        try {
            result = renderHtml(model, req, "messageTemplate");
        } catch (Exception e) {
            logger.debug("Render html error!", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        ContactDto friendDto = new ContactDto();
        friendDto.setId(friend.getId());
        friendDto.setFirstName(friend.getFirstName());
        friendDto.setLastName(friend.getLastName());
        logger.debug(friendDto.toString());
        return ResponseMessage.okMessage(friendDto, result);
    }

    @RequestMapping(value = "/friends/{id}/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage sendMessage(@RequestBody SendMessageDto message, @PathVariable("id") long id, HttpSession session, Model model, HttpServletRequest req) {
        logger.warn("sendMessage()");

        Contact user = getPersistUser(session);
        Contact friend = service.findContactById(id);
        Message newMessage = new Message(user, new Date(), message.getMessage());
        List<Message> messages = new ArrayList<>();
        try {
            messageService.saveMessage(newMessage);
            logger.debug(user.getConversation().toString());
            Chat currentChat = null;
            for (Chat chat : user.getConversation()) {
                if (chat.getUserTo().equals(friend)) {
                    currentChat = chat;
                    currentChat.getChatMessages().add(newMessage);
                    chatService.updateChat(currentChat);
                    for (Message m : currentChat.getChatMessages()) {
                        messages.add(m);
                    }
                }
            }
            if(currentChat==null){
                Chat newChat = new Chat(friend);
                Chat newChat2 = new Chat(user);
                newChat.getChatMessages().add(newMessage);
                newChat2.getChatMessages().add(newMessage);
                chatService.saveChat(newChat);
                chatService.saveChat(newChat2);
                service.addChatToContact(user, newChat);
                service.addChatToContact(friend, newChat2);
                for(Message m:newChat.getChatMessages()){
                    messages.add(m);
                }
            }
            service.updateContact(user);
        } catch (Exception e) {
            logger.debug("Send message error!", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        model.addAttribute("messages", messages);
        String result = null;
        try {
            result = renderHtml(model, req, "messageTemplate");
        } catch (Exception e) {
            logger.debug("Render html error!", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage(result);
    }

    @RequestMapping(value = "/friends/{id}/invokePost", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage invokePost(@PathVariable("id") long id) {
        logger.warn("invokePost()");
        Contact friend = null;
        try {
            friend = service.findContactById(id);
        }catch (Exception e){
            logger.debug("Invoke post error!", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        ContactDto friendDto = new ContactDto();
        if(friend==null) {
            return ResponseMessage.errorMessage(new NullPointerException().getMessage());
        }
        friendDto.setId(friend.getId());
        friendDto.setFirstName(friend.getFirstName());
        friendDto.setLastName(friend.getLastName());
        return ResponseMessage.okMessage(friendDto);
    }

    @RequestMapping(value = "/friends/{id}/sendPost", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage sendPost(@RequestBody SendMessageDto message, @PathVariable("id") long id, HttpSession session) {
        logger.warn("sendPost()");
        Contact user = getPersistUser(session);
        Contact friend = service.findContactById(id);
        Post newPost = new Post(user, message.getMessage(), new Date());
        try {
            service.addPostToContact(friend, postService.savePost(newPost));
        } catch (Exception e) {
            logger.debug("Send post error!", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage("");
    }

    @RequestMapping(value = "/friends/{id}/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addFriend(@PathVariable("id") long id, HttpSession session) {
        logger.warn("addFriend() : {}", id);

        Contact user = getPersistUser(session);
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

        Contact user = getPersistUser(session);
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

    @RequestMapping(value = "hobbies/saveHobby", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveHobby(@RequestBody HobbyDto hobbyDto, HttpSession session) {
        logger.warn("saveHobby()");
        try {
            Hobby newHobby = hobbyService.saveHobby(hobbyDto.getTitle(), hobbyDto.getDescription());
            logger.debug(newHobby.toString());
            Contact user = getPersistUser(session);
            service.addHobbyToContact(user, newHobby);
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
        Contact user = getPersistUser(session);
        logger.info("User " + user.getFirstName() + " tries to edit hobby with id:" + id);
        Hobby hobby = hobbyService.findHobbyById(id);
        hobby.setTitle(hobbyDto.getTitle());
        hobby.setDescription(hobbyDto.getDescription());
        try {
            hobbyService.updateHobby(hobby);
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

        Contact user = getPersistUser(session);
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

    @RequestMapping(value = "places/savePlace", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage savePlace(@RequestBody PlaceDto placeDto, HttpSession session) {
        logger.warn("savePlace()");
        try {
            Place newPlace = placeService.savePlace(placeDto.getTitle(), placeDto.getDescription(), placeDto.getLatitude(), placeDto.getLongitude());
            logger.debug(newPlace.toString());
            Contact user = getPersistUser(session);
            service.addPlaceToContact(user,newPlace);
        } catch (Exception e) {
            logger.debug("Save place error!", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        return ResponseMessage.okMessage("Success! New place saved:)");
    }

    @RequestMapping(value = "/places/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editPlace(@PathVariable("id") long id, @RequestBody PlaceDto placeDto, HttpSession session) {
        logger.warn("editPlace() : {}", id);

        Contact user = getPersistUser(session);
        Place place = placeService.findPlaceById(id);
        place.setTitle(placeDto.getTitle());
        place.setDescription(placeDto.getDescription());
        place.setLatitude(placeDto.getLatitude());
        place.setLongitude(placeDto.getLongitude());
        try {
            placeService.updatePlace(place);
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

        Contact user = getPersistUser(session);
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

    @RequestMapping(value = "/people/show", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage showPeople(HttpSession session) {
        logger.warn("showPeople()");

        Contact user = getPersistUser(session);
        Set<Contact> contacts = service.findAllContacts();
        contacts.remove(user);
        logger.debug(contacts.toString());
        session.setAttribute("contacts", contacts);
        return ResponseMessage.okMessage("");
    }

    @RequestMapping(value = "/people/filter/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addFilter(@RequestBody Filter filter, HttpSession session) {
        logger.warn("addFilter()");
        Contact user = getPersistUser(session);
        Set<Contact> filteredContacts = new HashSet<>();
        String fmtTitle = filter.getTextFilter();
        if(!(fmtTitle.substring(0,1).equals(fmtTitle.substring(0, 1).toUpperCase()))) {
            fmtTitle = filter.getTextFilter().substring(0, 1).toUpperCase()+filter.getTextFilter().substring(1);
        }
        if (filter.getSelectNumber() == 1) {
            try {
                Hobby selectedHobby = hobbyService.findHobbyByTitle(fmtTitle);
                filteredContacts = service.findAllContactsWithHobby(selectedHobby);
                filteredContacts.remove(user);
            } catch (Exception e) {
                logger.debug("Filter contacts for hobby error", e);
                return ResponseMessage.errorMessage(e.getMessage());
            }
        }else if(filter.getSelectNumber()==2){
            try{
                Place selectedPlace = placeService.findPlaceByTitle(fmtTitle);
                filteredContacts = service.findAllContactsForPlace(selectedPlace);
                filteredContacts.remove(user);
            }catch (Exception e){
                logger.debug("Filter contacts for place error", e);
                return ResponseMessage.errorMessage(e.getMessage());
            }
        }
        session.setAttribute("contacts", filteredContacts);
        return ResponseMessage.okMessage("");
    }

    @RequestMapping(value = "/people/filter/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeFilter(HttpSession session) {
        logger.warn("removeFilter()");
        Contact user = getPersistUser(session);
        Set<Contact> filteredContacts = new HashSet<>();
        try {
            filteredContacts = service.findAllContacts();
            filteredContacts.remove(user);
        } catch (Exception e) {
            logger.debug("Remove filter error", e);
            return ResponseMessage.errorMessage(e.getMessage());
        }
        session.setAttribute("contacts", filteredContacts);
        return ResponseMessage.okMessage("");
    }

    private String renderHtml(Model model, HttpServletRequest req, String view){
        View resolvedView = null;
        String result = null;
        MockHttpServletResponse mockResp = new MockHttpServletResponse();
        try{
            resolvedView = resolver.resolveViewName(view, Locale.getDefault());
            resolvedView.render(model.asMap(), req, mockResp);
            result = mockResp.getContentAsString();
        } catch (Exception e){
            logger.debug("Render html error!", e);
        }
        return result;
    }

    private Contact getPersistUser(HttpSession session){
        Contact user = (Contact)session.getAttribute("USER");
        if(user==null){
            Contact empty = new Contact();
            empty.setFirstName("emptySession");
            return empty;
        }
        service.refreshContact(user);
        return user;
    }
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResponseMessage handleException(Exception e) {
//        logger.debug("handlerException()");
//        return ResponseMessage.errorMessage(e.getMessage());
//    }
}
