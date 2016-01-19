package net.bondar.web.dao.impl;

import net.bondar.web.dao.inter.PlaceDao;
import net.bondar.web.model.Place;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by AzeraL on 07.10.2015.
 */
@Repository
public class PlaceDaoImpl extends AbstractDaoImpl<Place> implements PlaceDao {
    public Place findPlaceByTitle(String title) {
        Criteria criteria = getSession().createCriteria(Place.class);
        criteria.add(Restrictions.eq("title", title));
        return (Place) criteria.uniqueResult();
    }
}
