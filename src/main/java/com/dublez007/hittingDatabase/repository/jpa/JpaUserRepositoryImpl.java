package com.dublez007.hittingDatabase.repository.jpa;

import com.dublez007.hittingDatabase.model.User;
import com.dublez007.hittingDatabase.repository.UserRepository;
import com.dublez007.hittingDatabase.util.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;


    @Override
    @Transactional
    public User save(User user) {
        if(user.isNew()){
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        throw new NotFoundException("Method JpaUserRepositoryImpl.getByEmail() is not implemented.");
    }

    @Override
    public boolean remove(int id) {
        throw new NotFoundException("Method JpaUserRepositoryImpl.remove() is not implemented.");
    }

    @Override
    public List<User> getAll() {
        throw new NotFoundException("Method JpaUserRepositoryImpl.getAll() is not implemented.");
    }
}
