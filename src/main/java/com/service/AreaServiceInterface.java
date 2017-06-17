package com.service;

import com.entity.Area;
import com.entity.User;
import com.exception.AreaException;
import com.exception.TableException;

import java.util.List;

public interface AreaServiceInterface {
    List<Area> getAll();
    Area getById(int id) throws AreaException;

    List<User> getAvalibleUsers(Area area) throws AreaException;

    void add(Area area);

    void update(Area area) throws AreaException;
    void delete(int id) throws AreaException;
}
