package net.bondar.web.service;

import net.bondar.web.dao.inter.ContactDao;
import net.bondar.web.exceptions.*;
import net.bondar.web.model.*;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Azeral on 24.11.2015.
 */
@Service
@Transactional
public class ContactService {

    @Autowired
    private ContactDao contactDao;
    @Autowired
    SessionFactory sessionFactory;



//    public Contact saveContact(String firstName, String lastName, Date birthDate, String userName, String password) {
//        if(firstName==null)throw new IllegalArgumentException("Contact firstName is null");
//        else if(lastName==null) throw new IllegalArgumentException("Contact lastName is null");
//        else if(!firstName.matches("^\\D*$")) throw new IllegalArgumentException("Contact firstName contains digits");
//        else if(!lastName.matches("^\\D*$")) throw new IllegalArgumentException("Contact lastName contains digits");
//        else if(userName==null)throw new IllegalArgumentException("Contact login is null");
//        else if(password==null) throw new IllegalArgumentException("Contact password is null");
//        Contact result = new Contact(firstName, lastName, birthDate, userName, password);
//        return contactDao.save(result);
//    }

    public Contact saveContact(Contact contact) {
        return contactDao.save(contact);
    }

    public Contact updateContact(Contact contact){
        return contactDao.update(contact);
    }

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

    public void deleteContactById(long id) throws Exception {
        Contact contact = contactDao.findById(id);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class, "contact");
        criteria.createAlias("contact.friendList", "friendList");
        criteria.add(Restrictions.eq("friendList.id", contact.getId()));
        List<Contact> contacts = criteria.list();
        for(Contact user:contacts){
            removeFriendship(user, contact);
        }
        contactDao.delete(id);
    }


    public Contact findContactById(long id) {
        Contact result = contactDao.findById(id);
        if(result==null) throw new NoSuchContactException();
        return result;
    }

    public Contact findContactByUserName(String userName) {
        return contactDao.findContactByUserName(userName);
    }

    public Long count(String userName){
        return contactDao.count(userName);
    }

    public Collection<Contact> findAllContacts() {
        Collection<Contact> result = contactDao.getAll();
        return result;
    }

    public Collection<Contact>findAllContactsWithHobby(Hobby hobby) {
        Collection<Contact> result = contactDao.getAllContactsWithHobby(hobby);
        if(result.isEmpty()) throw new ContactsNotFoundException();
        else return result;
    }

    public Collection<Contact> findAllContactsForPlace(Place place) {
        Collection<Contact> result = contactDao.getAllContactsForPlace(place);
        if(result.isEmpty()) throw new ContactsNotFoundException();
        else return result;
    }


    public void addHobbyToContact(Contact contact, Hobby hobby) {
        if(contact.getHobbies().contains(hobby)) throw new ExistingHobbyException();
        contact.getHobbies().add(hobby);
        contactDao.update(contact);
        contactDao.flush();
    }

    public void addPlaceToContact(Contact contact, Place place) {
        if(contact.getPlaces().contains(place)) throw new ExistingPlaceException();
        contact.getPlaces().add(place);
        contactDao.update(contact);
        contactDao.flush();
    }

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

    public void removeFriendship(Contact who, Contact with) throws Exception {
        if(who.getFriendList().isEmpty()) throw new NoSuchFriendException();
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

    public void addChatToContact(Contact contact, Chat chat) {
        contact.getConversation().add(chat);
        contactDao.update(contact);
        contactDao.flush();
    }

    public Set<Contact> getFriendList(Contact contact) {
        Set<Contact> result = contact.getFriendList();
        if(result.isEmpty()) throw new EmptyFriendListException();
        return result;
    }

    public Chat getConversation(Contact who, Contact with) {
        Chat result = contactDao.getConversation(who, with);
        if(result==null) throw new NoSuchConversationException();
        else return result;
    }

    public void addPostToContact(Contact contact, Post post) {
        contact.getPosts().add(post);
        contactDao.update(contact);
        contactDao.flush();
    }
}
