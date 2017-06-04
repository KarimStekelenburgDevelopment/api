package com.service;

import com.entity.User;
import com.exception.LoginException;

import java.util.List;
public interface UserServiceInterface {
    List<User> getAll();
    User getById(int id);
    boolean add(User user);

    User getUserByUsername(String username) throws LoginException;

    void update(User user);
    void delete(int id);

    boolean validatePassword(User user, String password);
}