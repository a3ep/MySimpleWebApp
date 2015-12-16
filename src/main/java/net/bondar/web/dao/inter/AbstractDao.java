package net.bondar.web.dao.inter;

import java.util.Collection;

/**
 * Created by AzeraL on 06.10.2015.
 */
public interface AbstractDao<T> {
    T save(T t);
    T update(T t);
    void refresh(T t);
    void delete(T t);
    void delete(long id);
    T findById(long id);
    Collection<T> getAll();
    void flush();
    void clear();
}
