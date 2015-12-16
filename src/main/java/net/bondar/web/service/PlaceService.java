package net.bondar.web.service;

import net.bondar.web.dao.inter.PlaceDao;
import net.bondar.web.model.Place;
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
public class PlaceService {

    @Autowired
    private PlaceDao placeDao;
    @Autowired
    SessionFactory sessionFactory;


    public Place savePlace(String title, String description, double latitude, double longitude) {
        if(title==null) throw new IllegalArgumentException("Place title is null");
        else if(description==null) throw new IllegalArgumentException("Place description is null");
        else if(latitude<-90||latitude>90) throw new IllegalArgumentException("Place latitude <-90 or >90");
        else if(longitude<-180||longitude>180) throw new IllegalArgumentException("Place longitude <-180 or >180");
        Place place = new Place(title, description, latitude, longitude);
        return placeDao.save(place);
    }

    public Place savePlace(Place place){
        return placeDao.save(place);
    }

    public Place findPlaceById(long id) {
        return placeDao.findById(id);
    }

    public Collection<Place> findAllPlaces() {
        return placeDao.getAll();
    }
}
