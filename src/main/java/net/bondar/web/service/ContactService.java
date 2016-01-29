package net.bondar.web.service;

import net.bondar.web.dao.inter.ContactDao;
import net.bondar.web.exceptions.EmptyObjectException;
import net.bondar.web.exceptions.ExistingObjectException;
import net.bondar.web.exceptions.NoSuchObjectException;
import net.bondar.web.model.*;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Contact saveContact(Contact contact) {
        Contact savedContact = contactDao.save(contact);
        contactDao.flush();
        return savedContact;
    }

    public Contact updateContact(Contact contact) {
        Contact updatedContact = contactDao.update(contact);
        contactDao.flush();
        return updatedContact;
    }

    public void refreshContact(Contact contact) {
        contactDao.refresh(contact);
        contactDao.flush();
    }

    public void deleteContact(Contact contact) throws Exception {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class, "contact");
        criteria.createAlias("contact.friendList", "friendList");
        criteria.add(Restrictions.eq("friendList.id", contact.getId()));
        List<Contact> contacts = criteria.list();
        if (contacts.isEmpty()) {
            throw new EmptyObjectException();
        }
        for (Contact user : contacts) {
            removeFriendship(user, contact);
        }
        contactDao.delete(contact);
        contactDao.flush();
    }

    public void deleteContactById(long id) throws Exception {
        Contact contact = contactDao.findById(id);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class, "contact");
        criteria.createAlias("contact.friendList", "friendList");
        criteria.add(Restrictions.eq("friendList.id", contact.getId()));
        List<Contact> contacts = criteria.list();
        for (Contact user : contacts) {
            removeFriendship(user, contact);
        }
        contactDao.delete(id);
        contactDao.flush();
    }

    public Contact findContactById(long id) {
        Contact result = contactDao.findById(id);
        if (result == null) throw new NoSuchObjectException();
        return result;
    }

    public Contact findContactByUserName(String userName) {
        return contactDao.findContactByUserName(userName);
    }

    public Long count(String userName) {
        return contactDao.count(userName);
    }

    public Set<Contact> findAllContacts() {
        return contactDao.getAll();
    }

    public Set<Contact> findAllContactsWithHobby(Hobby hobby) {
        return contactDao.getAllContactsWithHobby(hobby);
    }

    public Set<Contact> findAllContactsForPlace(Place place) {
        return contactDao.getAllContactsForPlace(place);
    }

    public void addHobbyToContact(Contact contact, Hobby hobby) {
        if (contact.getHobbies().contains(hobby)) throw new ExistingObjectException();
        contact.getHobbies().add(hobby);
        contactDao.update(contact);
        contactDao.flush();
    }

    public void removeHobby(Contact contact, Hobby hobby) {
        if (contact.getHobbies().contains(hobby)) {
            contact.getHobbies().remove(hobby);
            contactDao.update(contact);
            contactDao.flush();
        }
    }

    public void addPlaceToContact(Contact contact, Place place) {
        if (contact.getPlaces().contains(place)) throw new ExistingObjectException();
        contact.getPlaces().add(place);
        contactDao.update(contact);
        contactDao.flush();
    }

    public void removePlace(Contact contact, Place place) {
        if (contact.getPlaces().contains(place)) {
            contact.getPlaces().remove(place);
            contactDao.update(contact);
            contactDao.flush();
        }
    }

    public void addPostToContact(Contact contact, Post post) {
        if (contact.getPosts().contains(post)) throw new ExistingObjectException();
        contact.getPosts().add(post);
        contactDao.update(contact);
        contactDao.flush();
    }

    public void removePost(Contact contact, Post post) {
        if (contact.getPosts().contains(post)) {
            contact.getPosts().remove(post);
            contactDao.update(contact);
            contactDao.flush();
        }
    }

    public void addFriendship(Contact who, Contact with) {
        if (who.getFriendList().contains(with) || with.getFriendList().contains(who))
            throw new ExistingObjectException();
        addFriend(who, with);
        addFriend(with, who);
    }

    private void addFriend(Contact contact, Contact friend) {
        contact.getFriendList().add(friend);
        contactDao.update(contact);
        contactDao.flush();
    }

    public void removeFriendship(Contact who, Contact with) throws Exception {
        if (!(who.getFriendList().contains(with)) || !(with.getFriendList().contains(who)))
            throw new NoSuchObjectException();
        removeFriend(who, with);
        removeFriend(with, who);
    }

    private void removeFriend(Contact contact, Contact friend) throws Exception {
        if (contact.getFriendList().contains(friend)) {
            contact.getFriendList().remove(friend);
            contactDao.update(contact);
            contactDao.flush();
        } else {
            throw new Exception("This contact is not a friend!");
        }
    }

    public void addChatToContact(Contact contact, Chat chat) {
        if (contact.getConversation().contains(chat)) throw new ExistingObjectException();
        contact.getConversation().add(chat);
        contactDao.update(contact);
        contactDao.flush();
    }

    public void removeChat(Contact contact, Chat chat) {
        if (contact.getConversation().contains(chat)) {
            contact.getConversation().remove(chat);
            contactDao.update(contact);
            contactDao.flush();
        }
    }

    public Set<Contact> getFriendList(Contact contact) {
        Set<Contact> result = contact.getFriendList();
        if (result.isEmpty()) throw new EmptyObjectException();
        return result;
    }

    public Chat getConversation(Contact who, Contact with) {
        Chat result = contactDao.getConversation(who, with);
        if (result == null) throw new NoSuchObjectException();
        else return result;
    }
}
