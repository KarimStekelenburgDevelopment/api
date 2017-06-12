package com.model;

import com.entity.User;
import com.exception.LoginException;
import com.exception.UserException;
import com.exception.UserRoleException;

import java.util.List;
public interface UserModelInterface {
    List<User> getAll();
    User getById(int id) throws UserException;

    User getUserByUsername(String username) throws UserException;

    void add(User user) throws UserRoleException, UserException;
    void update(User user) throws UserException;
    void delete(int id) throws UserException;
    boolean exists(String username);

    boolean validatePassword(User user, String password);
}