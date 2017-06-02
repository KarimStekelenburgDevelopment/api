package com.service;

import com.entity.User;

import java.util.List;
public interface UserServiceInterface {
    List<User> getAll();
    User getById(int id);
    boolean add(User user);
    void update(User user);
    void delete(int id);
}