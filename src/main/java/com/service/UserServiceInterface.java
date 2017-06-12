package com.service;

import com.entity.User;
import com.exception.UserException;
import com.exception.UserRoleException;

import java.util.List;
public interface UserServiceInterface {

    List<User> getAll();

    User getById(int id) throws UserException;

    void add(User user) throws UserException, UserRoleException;

    User getUserByUsername(String username) throws UserException;

    void update(User user) throws UserException;
    void delete(int id) throws UserException;

    boolean validatePassword(User user, String password);
}