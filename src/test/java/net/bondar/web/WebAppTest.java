package net.bondar.web;

import net.bondar.web.dao.inter.*;
import net.bondar.web.exceptions.*;
import net.bondar.web.model.*;
import net.bondar.web.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.doReturn;

/**
 * Created by AzeraL on 21.01.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class WebAppTest {

    private Contact contact1;
    private Contact contact2;
    private Chat chat;
    private Message message;
    private Hobby hobby1;
    private Place place1;
    private Post post1;

    @Mock
    private ChatDao chatDao;
    @Mock
    private ContactDao contactDao;
    @Mock
    private HobbyDao hobbyDao;
    @Mock
    private MessageDao messageDao;
    @Mock
    private PlaceDao placeDao;
    @Mock
    private PostDao postDao;

    @InjectMocks
    private ChatService chatService = new ChatService();
    @InjectMocks
    private ContactService contactService = new ContactService();
    @InjectMocks
    private HobbyService hobbyService = new HobbyService();
    @InjectMocks
    private MessageService messageService = new MessageService();
    @InjectMocks
    private PlaceService placeService = new PlaceService();
    @InjectMocks
    private PostService postService = new PostService();

    @Before
    public void initMocks(){
        contact1 = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        doReturn(contact1).when(contactDao).findById(1);
        contact2 = new Contact("SecondName", "SecondLastName", new Date(1900-1900, 3, 13), "second", "258456", "258456");
        doReturn(contact2).when(contactDao).findById(2);
        hobby1 = new Hobby("FirstHobby", "FirstDescription");
        doReturn(hobby1).when(hobbyDao).findById(21);
        message = new Message(contact1, contact2, new Date(), "Привет!");
        doReturn(message).when(messageDao).findById(31);
        place1 = new Place("FirstPlace", "FirstDescription", 12.0, 12.0);
        doReturn(place1).when(placeDao).findById(41);
        post1 = new Post(contact1, "Привет!", new Date());
        doReturn(post1).when(postDao).findById(51);
        chat = new Chat();
        chat.getMessages().add(message);
        doReturn(chat).when(chatDao).findById(61);
    }


    @Test
    public void testSaveContact(){
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        doReturn(contact1).when(contactDao).save(user);
        assertEquals(user, contactService.saveContact(user));
    }

    @Test
    public void testUpdateContact(){
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        doReturn(contact1).when(contactDao).update(user);
        assertEquals(user, contactService.updateContact(user));
    }

    @Test(expected = NoSuchObjectException.class)
    public void testFindContactById() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        doReturn(null).when(contactDao).findById(user.getId());
        assertNotNull(contactService.findContactById(user.getId()));
    }

    @Test(expected = NoSuchObjectException.class)
    public void testFindContactByUserName() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        doReturn(null).when(contactDao).findContactByUserName(user.getUserName());
        assertNotNull(contactService.findContactByUserName(user.getUserName()));
    }

    @Test
    public void testCount() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        long count = 0;
        doReturn(count).when(contactDao).count(user.getUserName());
        assertTrue(contactService.count(user.getUserName()) == 0);
    }

    @Test
    public void testFindAllContacts() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Contact user2 = new Contact("SecondName", "SecondLastName", new Date(1900-1900, 3, 13), "second", "258456", "258456");
        Set<Contact> contacts = new HashSet<>();
        contacts.add(user);
        contacts.add(user2);
        doReturn(contacts).when(contactDao).getAll();
        assertTrue(contactService.findAllContacts().equals(contacts));
    }

    @Test
    public void testFindAllContactsWithHobby() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Contact user2 = new Contact("SecondName", "SecondLastName", new Date(1900-1900, 3, 13), "second", "258456", "258456");
        Hobby hobby = hobbyService.findHobbyById(21);
        user.getHobbies().add(hobby);
        user2.getHobbies().add(hobby);
        Set<Contact> contacts = new HashSet<>();
        contacts.add(user);
        contacts.add(user2);
        doReturn(contacts).when(contactDao).getAllContactsWithHobby(hobby);
        assertTrue(contactService.findAllContactsWithHobby(hobby).equals(contacts));
    }

    @Test
    public void testFindAllContactsForPlaces() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Contact user2 = new Contact("SecondName", "SecondLastName", new Date(1900-1900, 3, 13), "second", "258456", "258456");
        Place place = placeService.findPlaceById(41);
        user.getPlaces().add(place);
        user2.getPlaces().add(place);
        Set<Contact> contacts = new HashSet<>();
        contacts.add(user);
        contacts.add(user2);
        doReturn(contacts).when(contactDao).getAllContactsForPlace(place);
        assertTrue(contactService.findAllContactsForPlace(place).equals(contacts));
    }

    @Test(expected = ExistingObjectException.class)
    public void testAddHobbyToContact() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Hobby hobby = hobbyService.findHobbyById(21);
        user.getHobbies().add(hobby);
        contactService.addHobbyToContact(user, hobby);
    }

    @Test
    public void removeHobby() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Hobby hobby = hobbyService.findHobbyById(21);
        user.getHobbies().add(hobby);
        contactService.removeHobby(user,hobby);
    }

    @Test(expected = ExistingObjectException.class)
    public void testAddPlaceToContact() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Place place = placeService.findPlaceById(41);
        user.getPlaces().add(place);
        contactService.addPlaceToContact(user, place);
    }

    @Test
    public void removePlace() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Place place = placeService.findPlaceById(41);
        user.getPlaces().add(place);
        contactService.removePlace(user, place);
    }

    @Test(expected = ExistingObjectException.class)
    public void testAddPostToContact() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Post post = postService.findPostById(51);
        user.getPosts().add(post);
        contactService.addPostToContact(user, post);
    }

    @Test
    public void removePost() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Post post = postService.findPostById(51);
        user.getPosts().add(post);
        contactService.removePost(user, post);
    }

    @Test(expected = ExistingObjectException.class)
    public void testAddFriendship_ExistingFriend(){
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Contact user2 = new Contact("SecondName", "SecondLastName", new Date(1900-1900, 3, 13), "second", "258456", "258456");
        user.getFriendList().add(user2);
        user2.getFriendList().add(user);
        contactService.addFriendship(user, user2);
    }

    @Test(expected = NoSuchObjectException.class)
    public void testRemoveFriendship_NoSuchFriend() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Contact user2 = new Contact("SecondName", "SecondLastName", new Date(1900-1900, 3, 13), "second", "258456", "258456");
        contactService.removeFriendship(user, user2);
    }

    @Test(expected = ExistingObjectException.class)
    public void testAddChatToContact() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Chat chat = chatService.findChatById(61);
        user.getConversation().add(chat);
        contactService.addChatToContact(user, chat);
    }

    @Test(expected = EmptyObjectException.class)
    public void getFriendList() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        contactService.getFriendList(user);
    }

    @Test(expected = NoSuchObjectException.class)
    public void getConversation() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Contact user2 = new Contact("SecondName", "SecondLastName", new Date(1900-1900, 3, 13), "second", "258456", "258456");
        contactService.getConversation(user, user2);
    }

    @Test
    public void testSaveChat(){
        Chat newChat = new Chat();
        newChat.getMessages().add(message);
        doReturn(chat).when(chatDao).save(newChat);
        assertEquals(newChat, chatService.saveChat(newChat));
    }

    @Test
    public void testUpdateChat(){
        Chat newChat = new Chat();
        newChat.getMessages().add(message);
        doReturn(chat).when(chatDao).update(newChat);
        assertEquals(newChat, chatService.updateChat(newChat));
    }

    @Test(expected = NoSuchObjectException.class)
    public void testFindChatById() throws Exception {
        Chat newChat = new Chat();
        doReturn(null).when(chatDao).findById(newChat.getId());
        assertNotNull(chatService.findChatById(newChat.getId()));
    }

    @Test(expected = NoSuchObjectException.class)
    public void testFindChatByUserToId() throws Exception {
        Contact user = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456");
        Chat newChat = new Chat();
        doReturn(null).when(chatDao).findChatByUserToId(user.getId());
        assertNotNull(chatService.findChatByUserToId(user.getId()));
    }

    @Test
    public void testSaveHobby(){
        Hobby hobby = new Hobby("FirstHobby", "FirstDescription");
        doReturn(hobby1).when(hobbyDao).save(hobby);
        assertEquals(hobby, hobbyService.saveHobby(hobby));
    }

    @Test
    public void testUpdateHobby(){
        Hobby hobby = new Hobby("FirstHobby", "FirstDescription");
        doReturn(hobby1).when(hobbyDao).update(hobby);
        assertEquals(hobby, hobbyService.updateHobby(hobby));
    }

    @Test
    public void testDeleteHobby(){
        Hobby hobby = new Hobby("FirstHobby", "FirstDescription");
        hobbyService.deleteHobby(hobby);
    }

    @Test(expected = NoSuchObjectException.class)
    public void testFindHobbyById() throws Exception {
        Hobby hobby = new Hobby("FirstHobby", "FirstDescription");
        doReturn(null).when(hobbyDao).findById(hobby.getId());
        assertNotNull(hobbyService.findHobbyById(hobby.getId()));
    }

    @Test(expected = NoSuchObjectException.class)
    public void testFindHobbyByTitle() throws Exception {
        Hobby hobby = new Hobby("FirstHobby", "FirstDescription");
        doReturn(null).when(hobbyDao).findHobbyByTitle(hobby.getTitle());
        assertNotNull(hobbyService.findHobbyByTitle(hobby.getTitle()));
    }

    @Test
    public void testFindAllHobbies() throws Exception {
        Hobby newHobby = new Hobby("FirstHobby", "FirstDescription");
        Hobby newHobby2 = new Hobby("SecondHobby", "SecondDescription");
        Set<Hobby> hobbies = new HashSet<>();
        hobbies.add(newHobby);
        hobbies.add(newHobby2);
        doReturn(hobbies).when(hobbyDao).getAll();
        assertTrue(hobbyService.findAllHobbies().equals(hobbies));
    }

    @Test
    public void testSaveMessage(){
        Message newMessage = new Message(contact1, contact2, new Date(), "Привет!");
        doReturn(message).when(messageDao).save(newMessage);
        assertEquals(newMessage, messageService.saveMessage(newMessage));
    }

    @Test(expected = NoSuchObjectException.class)
    public void testFindMessageById() throws Exception {
        Message newMessage = new Message(contact1, contact2, new Date(), "Привет!");
        doReturn(null).when(messageDao).findById(newMessage.getId());
        assertNotNull(messageService.findMessageById(newMessage.getId()));
    }

    @Test
    public void testSavePlace(){
        Place newPlace = new Place("FirstPlace", "FirstDescription", 12.0, 12.0);
        doReturn(place1).when(placeDao).save(newPlace);
        assertEquals(newPlace, placeService.savePlace(newPlace));
    }

    @Test
    public void testUpdatePlace(){
        Place newPlace = new Place("FirstPlace", "FirstDescription", 12.0, 12.0);
        doReturn(place1).when(placeDao).update(newPlace);
        assertEquals(newPlace, placeService.updatePlace(newPlace));
    }

    @Test
    public void testDeletePlace(){
        Place newPlace = new Place("FirstPlace", "FirstDescription", 12.0, 12.0);
        placeService.deletePlace(newPlace);
    }

    @Test(expected = NoSuchObjectException.class)
    public void testFindPlaceById() throws Exception {
        Place newPlace = new Place("FirstPlace", "FirstDescription", 12.0, 12.0);
        doReturn(null).when(placeDao).findById(newPlace.getId());
        assertNotNull(placeService.findPlaceById(newPlace.getId()));
    }

    @Test(expected = NoSuchObjectException.class)
    public void testFindPlaceByTitle() throws Exception {
        Place newPlace = new Place("FirstPlace", "FirstDescription", 12.0, 12.0);
        doReturn(null).when(placeDao).findPlaceByTitle(newPlace.getTitle());
        assertNotNull(placeService.findPlaceByTitle(newPlace.getTitle()));
    }

    @Test
    public void testFindAllPlaces() throws Exception {
        Place newPlace = new Place("FirstPlace", "FirstDescription", 12.0, 12.0);
        Place newPlace2 = new Place("SecondPlace", "SecondDescription", 21.0, 21.0);
        Set<Place> places = new HashSet<>();
        places.add(newPlace);
        places.add(newPlace2);
        doReturn(places).when(placeDao).getAll();
        assertTrue(placeService.findAllPlaces().equals(places));
    }

    @Test
    public void testSavePost(){
        Post newPost = new Post(contact1, "Привет!", new Date());
        doReturn(post1).when(postDao).save(newPost);
        assertEquals(newPost, postService.savePost(newPost));
    }

    @Test
    public void testUpdatePost(){
        Post newPost = new Post(contact1, "Привет!", new Date());
        doReturn(post1).when(postDao).update(newPost);
        assertEquals(newPost, postService.updatePost(newPost));
    }

    @Test
    public void testDeletePost(){
        Post newPost = new Post(contact1, "Привет!", new Date());
        postService.deletePost(newPost);
    }

    @Test(expected = NoSuchObjectException.class)
    public void testFindPostById() throws Exception {
        Post newPost = new Post(contact1, "Привет!", new Date());
        doReturn(null).when(postDao).findById(newPost.getId());
        assertNotNull(postService.findPostById(newPost.getId()));
    }


}
