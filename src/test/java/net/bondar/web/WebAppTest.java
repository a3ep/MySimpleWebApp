package net.bondar.web;

import net.bondar.web.configuration.HibernateConfig;
import net.bondar.web.dao.inter.*;
import net.bondar.web.model.*;
import net.bondar.web.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.doReturn;

/**
 * Created by AzeraL on 21.01.2016.
 */
@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {HibernateConfig.class})
public class WebAppTest {

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
        Contact contact1 = new Contact("FirstName", "FirstLastName", new Date(1990-1900, 8, 18), "first", "258456", "258456", "resources/img/no-photo.png");
        doReturn(contact1).when(contactDao).findById(1);
        Contact contact2 = new Contact("SecondName", "SecondLastName", new Date(1900-1900, 3, 13), "second", "258456", "258456", "resources/img/no-photo.png");
        doReturn(contact2).when(contactDao).findById(2);
        Hobby hobby1 = new Hobby("FirstHobby", "FirstDescription");
        doReturn(hobby1).when(hobbyDao).findById(21);
        Hobby hobby2 = new Hobby("SecondHobby", "SecondDescription");
        doReturn(hobby2).when(hobbyDao).findById(22);
        Message message1 = new Message(contact1, new Date(), "Привет!");
        doReturn(message1).when(messageDao).findById(31);
        Place place1 = new Place("FirstPlace", "FirstDescription", 12.0, 12.0);
        doReturn(place1).when(placeDao).findById(41);
        Place place2 = new Place("SecondPlace", "SecondDescription", 21.0, 21.0);
        doReturn(place2).when(placeDao).findById(42);
        Post post1 = new Post(contact1, "Привет!", new Date());
        doReturn(post1).when(postDao).findById(51);
    }


    @Test
    public void testSaveContact(){
        Contact user = contactDao.findById(1);
        System.out.println(user.getId() +" "+user.getFirstName());
        doReturn(user).when(contactDao).save(user);
        Contact savedUser = contactService.saveContact(user);
        System.out.println(savedUser.getId() + " " + savedUser.getFirstName());
        assertTrue(user.equals(savedUser));
    }
}
