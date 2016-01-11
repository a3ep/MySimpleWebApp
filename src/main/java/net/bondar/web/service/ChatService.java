package net.bondar.web.service;

import net.bondar.web.dao.inter.ChatDao;
import net.bondar.web.model.Chat;
import net.bondar.web.model.Contact;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AzeraL on 14.12.2015.
 */
@Service
@Transactional
public class ChatService {

    @Autowired
    private ChatDao chatDao;
    @Autowired
    SessionFactory sessionFactory;

    public Chat saveChat(Contact userTo) {
        Chat chat = new Chat(userTo);
        return chatDao.save(chat);
    }

    public Chat saveChat(Chat chat){
        return chatDao.save(chat);
    }
    public Chat findChatById(long id) {
        return chatDao.findById(id);
    }
}
