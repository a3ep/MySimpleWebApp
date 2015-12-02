package net.bondar.web.service;

import net.bondar.web.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Azeral on 24.11.2015.
 */
public interface AppService {
    Contact saveContact(String firstName, String lastName, LocalDate birthDate, String login, String password);
    void deleteContact(Contact contact) throws Exception;
    Contact findContactById(long id);
    Contact findContactByLogin(String login, String password) throws Exception;
    Hobby saveHobby(String title, String description);
    Hobby findHobbyById(long id);
    void addHobbyToContact(Contact contact, Hobby hobby);
    Place savePlace(String title, String description, double latitude, double longitude);
    Place findPlaceById(long id);
    void addPlaceToContact(Contact contact, Place place);
    void addFriendship(Contact who, Contact with);
    void removeFriendship(Contact who, Contact with) throws Exception;
    Chat saveChat(Contact userTo);
    Chat findChatById(long id);
    void addChatToContact(Contact contact, Chat chat);
    Chat getConversation(Contact who, Contact with);
    Post savePost (Contact contactFrom, String content, LocalDateTime date);
    Post findPostById(long id);
    void addPostToContact(Contact contact, Post post);
    Set<Post> findPosts(Contact contact);
    Set<Contact> getFriendList(Contact contact);
    Collection<Contact> findAllContacts();
    Collection<Hobby> findAllHobbies();
    Collection<Place> findAllPlaces();
    Collection<Contact> findAllContactsWithHobby(Hobby hobby);
    Collection<Contact> findAllContactsForPlace(Place place);
}

