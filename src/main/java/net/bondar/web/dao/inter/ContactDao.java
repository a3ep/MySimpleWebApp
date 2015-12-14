package net.bondar.web.dao.inter;
import net.bondar.web.model.Chat;
import net.bondar.web.model.Contact;
import net.bondar.web.model.Hobby;
import net.bondar.web.model.Place;

import java.util.Set;

/**
 * Created by AzeraL on 06.10.2015.
 */
public interface ContactDao extends AbstractDao<Contact> {
    Long findByLogin(String login);
    Set<Contact> getAllContactsWithHobby(Hobby hobby);
    Set<Contact> getAllContactsForPlace(Place place);
    Chat getConversation(Contact who, Contact with);
}
