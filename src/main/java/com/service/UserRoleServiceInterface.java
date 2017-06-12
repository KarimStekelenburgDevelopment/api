package com.service;

import com.entity.UserRole;

import java.util.List;

public interface UserRoleServiceInterface {
    List<UserRole> getAll();
    UserRole getById(int id);
    boolean add(UserRole role);
    void update(UserRole role);
    void delete(int id);
}
