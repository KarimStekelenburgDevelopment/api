package com.service;

import com.entity.User;

import java.util.List;
public interface UserServiceInterface {
    List<User> getAll();
    User getById(int id);
    boolean add(User user);

    User getUserByUsername(String username);

    void update(User user);
    void delete(int id);
}