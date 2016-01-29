package net.bondar.web.service;

import net.bondar.web.dao.inter.PlaceDao;
import net.bondar.web.exceptions.NoSuchObjectException;
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
        if (title == null) throw new IllegalArgumentException("Place title is null");
        else if (description == null) throw new IllegalArgumentException("Place description is null");
        else if (latitude < -90 || latitude > 90) throw new IllegalArgumentException("Place latitude <-90 or >90");
        else if (longitude < -180 || longitude > 180)
            throw new IllegalArgumentException("Place longitude <-180 or >180");
        Place place = new Place(title, description, latitude, longitude);
        Place savedPlace = placeDao.save(place);
        placeDao.flush();
        return savedPlace;
    }

    public Place savePlace(Place place) {
        Place savedPlace = placeDao.save(place);
        placeDao.flush();
        return savedPlace;
    }

    public Place updatePlace(Place place) {
        Place updatedPlace = placeDao.update(place);
        placeDao.flush();
        return updatedPlace;
    }

    public void deletePlace(Place place) {
        placeDao.delete(place);
        placeDao.flush();
    }

    public void deletePlace(long id) {
        placeDao.delete(id);
        placeDao.flush();
    }

    public Place findPlaceById(long id) {
        Place result = placeDao.findById(id);
        if (result == null) throw new NoSuchObjectException();
        return result;
    }

    public Place findPlaceByTitle(String title) {
        Place result = placeDao.findPlaceByTitle(title);
        if (result == null) throw new NoSuchObjectException();
        return result;
    }

    public Place findPlaceByTitleOpt(String title) {
        return placeDao.findPlaceByTitleOpt(title);
    }

    public Collection<Place> findAllPlaces() {
        return placeDao.getAll();
    }
}
