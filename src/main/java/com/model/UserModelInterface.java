package com.model;

import com.entity.User;
import com.exception.LoginException;
import com.exception.UserException;

import java.util.List;
public interface UserModelInterface {
    List<User> getAll();
    User getById(int id);

    User getUserByUsername(String username) throws LoginException;

    void add(User user);
    void update(User user);
    void delete(int id);
    boolean exists(String username);

    boolean validatePassword(User user, String password);
}