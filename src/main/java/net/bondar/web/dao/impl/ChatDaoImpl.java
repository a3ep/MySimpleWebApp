package net.bondar.web.dao.impl;

import net.bondar.web.dao.inter.ChatDao;
import net.bondar.web.model.Chat;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by AzeraL on 09.10.2015.
 */
@Repository
public class ChatDaoImpl extends AbstractDaoImpl<Chat> implements ChatDao {
    public Chat findChatByUserToId(long id) {
        Criteria criteria = getSession().createCriteria(Chat.class, "chat");
        criteria.createAlias("chat.messages", "messages");
        criteria.createAlias("messages.userTo", "userTo");
        criteria.add(Restrictions.eq("userTo.id", id));
        return (Chat) criteria.uniqueResult();
    }
}
