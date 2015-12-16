package net.bondar.web.dao.impl;

import net.bondar.web.dao.inter.ContactDao;
import net.bondar.web.model.Chat;
import net.bondar.web.model.Contact;
import net.bondar.web.model.Hobby;
import net.bondar.web.model.Place;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by AzeraL on 07.10.2015.
 */
@Repository
public class ContactDaoImpl extends AbstractDaoImpl<Contact> implements ContactDao {

    @Override
    public Long count(String userName) {
        Criteria criteria = getSession().createCriteria(Contact.class);
        criteria.add(Restrictions.eq("userName", userName));
        criteria.setProjection(Projections.rowCount());
        return (Long)criteria.uniqueResult();
    }

    @Override
    public Contact findContactByUserName(String userName) {
        Criteria criteria = getSession().createCriteria(Contact.class);
        criteria.add(Restrictions.eq("userName", userName));
        return (Contact) criteria.uniqueResult();
    }

    @Override
    public Set<Contact> getAllContactsWithHobby(Hobby hobby) {
        Set<Contact> result = new HashSet<>();
        Criteria criteria = getSession().createCriteria(Contact.class, "contact");
        criteria.createAlias("contact.hobbies", "hobbies");
        criteria.add(Restrictions.eq("hobbies.id", hobby.getId()));
        List<Contact> contacts = criteria.list();
        result.addAll(contacts.stream().collect(Collectors.toList()));
        return result;
    }

    @Override
    public Set<Contact> getAllContactsForPlace(Place place) {
        Set<Contact> result = new HashSet<>();
        Criteria criteria = getSession().createCriteria(Contact.class, "contact");
        criteria.createAlias("contact.places", "places");
        criteria.add(Restrictions.eq("places.id", place.getId()));
        List<Contact> contacts = criteria.list();
        result.addAll(contacts.stream().collect(Collectors.toList()));
        return result;
    }

    public Chat getConversation(Contact who, Contact with) {
        Chat result = new Chat();
        for(Chat chat:who.getConversation()){
            if(chat.getUserTo().getId()==with.getId()){
                result=chat;
            }
        }
        return result;
    }
}
