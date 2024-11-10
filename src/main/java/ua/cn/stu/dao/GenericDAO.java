package ua.cn.stu.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


/**
 Usage Generic provide opportunities to optimize and
do easier to create new Entity and support entities
which was created before.

**/
public class GenericDAO<T> {

    //------------------------------------------------------
    private final Session session;
    private final Class<T> entityClass;


    //------------------------------------------------------
    public GenericDAO(Session session, Class<T> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
    }


    //------------------------------------------------------
    public T create(T entity) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        return entity;
    }


    //------------------------------------------------------
    public T update (T entity) {
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        return entity;
    }


    //------------------------------------------------------
    public void delete (T entity) {
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }


    //------------------------------------------------------
    public T findById(int id){
        return session.get(entityClass, id);
    }


    //------------------------------------------------------
    public List<T> findAll() {
        return session.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    //------------------------------------------------------
    protected Session getSession() {
        return session;
    }
}
