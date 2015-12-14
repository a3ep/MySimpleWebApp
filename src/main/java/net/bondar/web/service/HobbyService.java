package net.bondar.web.service;

import net.bondar.web.dao.inter.HobbyDao;
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
        if(title==null) throw new IllegalArgumentException("Hobby title is null");
        else if(description==null) throw new IllegalArgumentException("Hobby description is null");
        Hobby hobby=new Hobby(title, description);
        return hobbyDao.save(hobby);
    }

    public Hobby findHobbyById(long id) {
        return hobbyDao.findById(id);
    }

    public Collection<Hobby> findAllHobbies() {
        return hobbyDao.getAll();
    }
}
