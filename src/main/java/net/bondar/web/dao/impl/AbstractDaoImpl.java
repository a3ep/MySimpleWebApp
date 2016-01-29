package net.bondar.web.dao.impl;

import net.bondar.web.dao.inter.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Azeral on 11.11.2015.
 */
@Repository
public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {
    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> persistentClass;
    private Session session;

    public AbstractDaoImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSession(Session session) {
        this.session = session;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public T save(T t) {
        getSession().saveOrUpdate(t);
        return t;
    }

    public T update(T t) {
        getSession().saveOrUpdate(t);
        return t;
    }

    public void refresh(T t) {
        getSession().refresh(t);
    }

    public void delete(T t) {
        getSession().delete(t);
    }

    public void delete(long id) {
        getSession().delete(findById(id));
    }

    public T findById(long id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

    public Set<T> getAll() {
        return findByCriteria();
    }

    protected Set<T> findByCriteria(Criterion... criterion) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            criteria.add(c);
        }
        Collection<T> collection = criteria.list();
        Set<T> set = new HashSet<>();
        for (T t : collection) {
            set.add(t);
        }
        return set;
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }
}
