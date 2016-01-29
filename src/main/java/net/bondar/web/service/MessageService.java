package net.bondar.web.service;

import net.bondar.web.dao.inter.MessageDao;
import net.bondar.web.exceptions.NoSuchObjectException;
import net.bondar.web.model.Message;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Flash on 11.01.2016.
 */
@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    SessionFactory sessionFactory;

    public Message saveMessage(Message message) {
        Message savedMessage = messageDao.save(message);
        messageDao.flush();
        return savedMessage;
    }

    public Message findMessageById(long id) {
        Message result = messageDao.findById(id);
        if (result == null) throw new NoSuchObjectException();
        return result;
    }
}
