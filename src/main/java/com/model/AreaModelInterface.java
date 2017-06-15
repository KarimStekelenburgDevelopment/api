package com.model;

import com.entity.Area;
import com.exception.AreaException;

import java.util.List;

public interface AreaModelInterface {

    List<Area> getAll();

    Area getById(int id) throws AreaException;

    void add(Area area);

    void update(Area area) throws AreaException;

    void delete(int id) throws AreaException;
}
