package com.model;

import com.entity.UserRole;

import java.util.List;

public interface UserRoleModelInterface {
    List<UserRole> getAll();
    UserRole getById(int id);
    void add(UserRole role);
    void update(UserRole role);
    void delete(int id);
}
