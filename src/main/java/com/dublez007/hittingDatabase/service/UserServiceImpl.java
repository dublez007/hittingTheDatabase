package com.dublez007.hittingDatabase.service;

import com.dublez007.hittingDatabase.model.User;
import com.dublez007.hittingDatabase.model.UserRole;
import com.dublez007.hittingDatabase.repository.UserRepository;
import com.dublez007.hittingDatabase.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dublez007.hittingDatabase.util.ValidationUtil.checkNotFoundWithId;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        throw new NotFoundException("UserServiceImpl.delete() method is not yet implemented");
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        throw new NotFoundException("UserServiceImpl.getByEmail() method is not yet implemented");
    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
