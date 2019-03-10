package com.dublez007.hittingDatabase.repository;

import com.dublez007.hittingDatabase.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    //null if not found
    User get(int id);

    //null if not found
    User getByEmail(String email);

    //false if not found
    boolean remove(int id);


    List<User> getAll();
}
