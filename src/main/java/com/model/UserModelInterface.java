package com.model;

import com.entity.User;

import java.util.List;
public interface UserModelInterface {
    List<User> getAll();
    User getById(int id);

    User getUserByUsername(String username);

    void add(User user);
    void update(User user);
    void delete(int id);
    boolean exists(String username);
}