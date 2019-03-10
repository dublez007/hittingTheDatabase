package com.dublez007.hittingDatabase.repository.hibernate;

import com.dublez007.hittingDatabase.model.User;
import com.dublez007.hittingDatabase.repository.UserRepository;
import com.dublez007.hittingDatabase.util.NotFoundException;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class HibernateUserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateUserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e){
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    public User save(User user) {
        User result = null;
            if(user.isNew())
                return (User) currentSession().save(user);
            else
            {
                currentSession().saveOrUpdate(user);
                return user;
            }
    }

    @Override
    public User get(int id) {
        return (User)currentSession().get(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        throw new NotFoundException("Method HibernateUserRepositoryImpl.getByEmail() is not implemented.");
    }

    @Override
    public boolean remove(int id) {
        throw new NotFoundException("Method HibernateUserRepositoryImpl.remove() is not implemented.");
    }

    @Override
    public List<User> getAll() {
        throw new NotFoundException("Method HibernateUserRepositoryImpl.getAll() is not implemented.");
    }
}
