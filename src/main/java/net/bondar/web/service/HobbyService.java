package net.bondar.web.service;

import net.bondar.web.dao.inter.HobbyDao;
import net.bondar.web.exceptions.NoSuchObjectException;
import net.bondar.web.model.Hobby;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by AzeraL on 14.12.2015.
 */
@Service
@Transactional
public class HobbyService {

    @Autowired
    private HobbyDao hobbyDao;
    @Autowired
    SessionFactory sessionFactory;

    public Hobby saveHobby(String title, String description) {
        if (title == null) throw new IllegalArgumentException("Hobby title is null");
        else if (description == null) throw new IllegalArgumentException("Hobby description is null");
        Hobby hobby = new Hobby(title, description);
        Hobby savedHobby = hobbyDao.save(hobby);
        hobbyDao.flush();
        return savedHobby;
    }

    public Hobby saveHobby(Hobby hobby) {
        Hobby savedHobby = hobbyDao.save(hobby);
        hobbyDao.flush();
        return savedHobby;
    }

    public Hobby updateHobby(Hobby hobby) {
        Hobby updatedHobby = hobbyDao.update(hobby);
        hobbyDao.flush();
        return updatedHobby;
    }

    public void deleteHobby(Hobby hobby) {
        hobbyDao.delete(hobby);
        hobbyDao.flush();
    }

    public void deleteHobby(long id) {
        hobbyDao.delete(id);
        hobbyDao.flush();
    }

    public Hobby findHobbyById(long id) {
        Hobby result = hobbyDao.findById(id);
        if (result == null) throw new NoSuchObjectException();
        return result;
    }

    public Hobby findHobbyByTitle(String title) {
        Hobby result = hobbyDao.findHobbyByTitle(title);
        if (result == null) throw new NoSuchObjectException();
        return result;
    }

    public Hobby findHobbyDyTitleOpt(String title) {
        return hobbyDao.findHobbyByTitleOpt(title);
    }

    public Collection<Hobby> findAllHobbies() {
        return hobbyDao.getAll();
    }
}
