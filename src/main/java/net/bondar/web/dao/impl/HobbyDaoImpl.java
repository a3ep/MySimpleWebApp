package net.bondar.web.dao.impl;

import net.bondar.web.dao.inter.HobbyDao;
import net.bondar.web.model.Hobby;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AzeraL on 07.10.2015.
 */
@Repository
public class HobbyDaoImpl extends AbstractDaoImpl<Hobby> implements HobbyDao {
    public Hobby findHobbyByTitle(String title) {
        Criteria criteria = getSession().createCriteria(Hobby.class);
        criteria.add(Restrictions.eq("title", title));
        return (Hobby) criteria.uniqueResult();
    }

    public Hobby findHobbyByTitleOpt(String title) {
        Hobby result = null;
        Criteria criteria = getSession().createCriteria(Hobby.class);
        List<Hobby> hobbies = criteria.list();
        for (Hobby hobby : hobbies) {
            if (hobby.getTitle().contains(title)) {
                result = hobby;
            }
        }
        return result;
    }
}
