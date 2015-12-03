package net.bondar.web.service;

import net.bondar.web.dao.inter.*;
import net.bondar.web.exceptions.*;
import net.bondar.web.model.*;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Azeral on 24.11.2015.
 */
@Service
@Transactional
public class AppServiceImpl implements AppService{

    @Autowired
    private ContactDao contactDao;
    @Autowired
    private HobbyDao hobbyDao;
    @Autowired
    private ChatDao chatDao;
    @Autowired
    private PlaceDao placeDao;
    @Autowired
    private PostDao postDao;
    @Autowired
    SessionFactory sessionFactory;


    @Override
    public Contact saveContact(String firstName, String lastName, Date birthDate, String login, String password) {
        if(firstName==null)throw new IllegalArgumentException("Contact firstName is null");
        else if(lastName==null) throw new IllegalArgumentException("Contact lastName is null");
        else if(!firstName.matches("^\\D*$")) throw new IllegalArgumentException("Contact firstName contains digits");
        else if(!lastName.matches("^\\D*$")) throw new IllegalArgumentException("Contact lastName contains digits");
        else if(login==null)throw new IllegalArgumentException("Contact login is null");
        else if(password==null) throw new IllegalArgumentException("Contact password is null");
        Contact result = new Contact(firstName, lastName, birthDate, login, password);
        return contactDao.save(result);
    }

    @Override
    public void deleteContact(Contact contact) throws Exception {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class, "contact");
        criteria.createAlias("contact.friendList", "friendList");
        criteria.add(Restrictions.eq("friendList.id", contact.getId()));
        List<Contact> contacts = criteria.list();
        for(Contact user:contacts){
            removeFriendship(user, contact);
        }
        contactDao.delete(contact);
    }

    @Override
    public Contact findContactById(long id) {
        Contact result = contactDao.findById(id);
        if(result==null) throw new NoSuchContactException();
        return result;
    }

    @Override
    public Contact findContactByLogin(String login, String password) throws Exception {
        Contact result = contactDao.findByLogin(login, password);
        if(result==null) throw new NoSuchContactException();
        return result;
    }

    @Override
    public Hobby saveHobby(String title, String description) {
        if(title==null) throw new IllegalArgumentException("Hobby title is null");
        else if(description==null) throw new IllegalArgumentException("Hobby description is null");
        Hobby hobby=new Hobby(title, description);
        return hobbyDao.save(hobby);
    }

    @Override
    public Hobby findHobbyById(long id) {
        return hobbyDao.findById(id);
    }

    @Override
    public void addHobbyToContact(Contact contact, Hobby hobby) {
        if(contact.getHobbies().contains(hobby)) throw new ExistingHobbyException();
        contact.getHobbies().add(hobby);
        contactDao.update(contact);
        contactDao.flush();
    }

    @Override
    public Place savePlace(String title, String description, double latitude, double longitude) {
        if(title==null) throw new IllegalArgumentException("Place title is null");
        else if(description==null) throw new IllegalArgumentException("Place description is null");
        else if(latitude<-90||latitude>90) throw new IllegalArgumentException("Place latitude <-90 or >90");
        else if(longitude<-180||longitude>180) throw new IllegalArgumentException("Place longitude <-180 or >180");
        Place place = new Place(title, description, latitude, longitude);
        return placeDao.save(place);
    }

    @Override
    public Place findPlaceById(long id) {
        return placeDao.findById(id);
    }

    @Override
    public void addPlaceToContact(Contact contact, Place place) {
        if(contact.getPlaces().contains(place)) throw new ExistingPlaceException();
        contact.getPlaces().add(place);
        contactDao.update(contact);
        contactDao.flush();
    }

    @Override
    public void addFriendship(Contact who, Contact with) {
        if(who.getFriendList().contains(with)||with.getFriendList().contains(who)) throw new ExistingFriendException();
        addFriend(who, with);
        addFriend(with, who);
    }

    private void addFriend(Contact contact, Contact friend){
        contact.getFriendList().add(friend);
        contactDao.update(contact);
        contactDao.flush();
    }

    @Override
    public void removeFriendship(Contact who, Contact with) throws Exception {
        if(who.getFriendList().isEmpty()||!who.getFriendList().contains(with)) throw new NoSuchFriendException();
        removeFriend(who, with);
        removeFriend(with, who);
    }

    private void removeFriend(Contact contact, Contact friend) throws Exception {
        if(contact.getFriendList().contains(friend)){
            contact.getFriendList().remove(friend);
            contactDao.update(contact);
            contactDao.flush();
        }else{
            throw new Exception("This contact is not a friend!");
        }
    }

    @Override
    public Chat saveChat(Contact userTo) {
        Chat chat = new Chat(userTo);
        return chatDao.save(chat);
    }

    @Override
    public Chat findChatById(long id) {
        return chatDao.findById(id);
    }

    @Override
    public void addChatToContact(Contact contact, Chat chat) {
        contact.getConversation().add(chat);
        contactDao.update(contact);
        contactDao.flush();
    }

    @Override
    public Set<Contact> getFriendList(Contact contact) {
        Set<Contact> result = contact.getFriendList();
        if(result.isEmpty()) throw new EmptyFriendListException();
        return result;
    }

    @Override
    public Chat getConversation(Contact who, Contact with) {
        Chat result = contactDao.getConversation(who, with);
        if(result==null) throw new NoSuchConversationException();
        else return result;
    }

    @Override
    public Post savePost(Contact contactFrom, String content, LocalDateTime date) {
        Post post = new Post(contactFrom, content, date);
        return postDao.save(post);
    }

    @Override
    public Post findPostById(long id) {
        return postDao.findById(id);
    }

    @Override
    public void addPostToContact(Contact contact, Post post) {
        contact.getPosts().add(post);
        contactDao.update(contact);
        contactDao.flush();
    }

    @Override
    public Set<Post> findPosts(Contact contact) {
        return contact.getPosts();
    }

    @Override
    public Collection<Contact> findAllContacts() {
        Collection<Contact> result = contactDao.getAll();
        if(result.isEmpty()) throw new EmptyContactCollectionException();
        else return result;
    }

    @Override
    public Collection<Hobby> findAllHobbies() {
        return hobbyDao.getAll();
    }

    @Override
    public Collection<Place> findAllPlaces() {
        return placeDao.getAll();
    }

    @Override
    public Collection<Contact>findAllContactsWithHobby(Hobby hobby) {
        Collection<Contact> result = contactDao.getAllContactsWithHobby(hobby);
        if(result.isEmpty()) throw new ContactsNotFoundException();
        else return result;
    }

    @Override
    public Collection<Contact> findAllContactsForPlace(Place place) {
        Collection<Contact> result = contactDao.getAllContactsForPlace(place);
        if(result.isEmpty()) throw new ContactsNotFoundException();
        else return result;
    }
}
