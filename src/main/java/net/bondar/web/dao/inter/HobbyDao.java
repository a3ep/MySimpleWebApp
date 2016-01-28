package net.bondar.web.dao.inter;


import net.bondar.web.model.Hobby;

/**
 * Created by AzeraL on 06.10.2015.
 */
public interface HobbyDao extends AbstractDao<Hobby> {
    Hobby findHobbyByTitle(String title);
    Hobby findHobbyByTitleOpt(String title);
}
