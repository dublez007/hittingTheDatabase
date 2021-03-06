package com.dublez007.hittingDatabase.service;

import com.dublez007.hittingDatabase.model.User;
import com.dublez007.hittingDatabase.util.NotFoundException;

import java.util.List;

public interface UserService {
    User create (User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    List<User> getAll();
}
