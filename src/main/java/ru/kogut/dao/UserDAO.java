package ru.kogut.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.kogut.model.UserEntity;
import ru.kogut.repository.UserRepository;
import ru.kogut.utils.HibernateSessionFactoryUtil;

import java.util.List;

/**
 * @author S.Kogut on 2019-03-25
 */
public class UserDAO implements UserRepository {

   public void create(UserEntity user) {
        final Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }catch (Exception e){
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            if (session.isOpen()) session.close();
        }
    }

    public List<UserEntity> findAll() {
        final Session session = HibernateSessionFactoryUtil.getSession();
        final List<UserEntity> userList = session.createQuery("FROM UserEntity", UserEntity.class).list() ;
        if (session.isOpen()) session.close();
        return userList;
    }

    public UserEntity findOne(String id) {
        final Session session = HibernateSessionFactoryUtil.getSession();
        final UserEntity user = session.get(UserEntity.class,id);
        if (session.isOpen()) session.close();
        return user;
    }

    public void update(UserEntity user) {
        final Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        }catch (Exception e){
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            if (session.isOpen()) session.close();
        }
    }

    public void deleteById(String id) {
        final Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            final Query query = session.createQuery("DELETE UserEntity WHERE id =:id");
            query.setParameter("id", id);
            query.executeUpdate();
            tx.commit();
        }catch (Exception e){
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            if (session.isOpen()) session.close();
        }

    }

    public void delete(UserEntity user) {
        final Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        }catch (Exception e){
            if (tx != null) tx.rollback();
        }finally {
            if (session.isOpen()) session.close();
        }
    }
}
