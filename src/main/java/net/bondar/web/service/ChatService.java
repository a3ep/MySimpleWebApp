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
        Chat savedChat = chatDao.save(chat);
        chatDao.flush();
        return savedChat;
    }

    public Chat saveChat(Chat chat){
        Chat savedChat = chatDao.save(chat);
        chatDao.flush();
        return savedChat;
    }

    public Chat updateChat(Chat chat){
        Chat updatedChat = chatDao.update(chat);
        chatDao.flush();
        return updatedChat;
    }
    public Chat findChatById(long id) {
        return chatDao.findById(id);
    }
}
