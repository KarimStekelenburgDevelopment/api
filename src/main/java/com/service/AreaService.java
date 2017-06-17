package com.service;

import com.entity.Area;
import com.entity.User;
import com.exception.AreaException;
import com.model.AreaModelInterface;
import com.model.UserModelInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService implements AreaServiceInterface{

    @Autowired
    private AreaModelInterface areaModel;

    @Autowired
    private UserModelInterface userModel;

    @Override
    public List<Area> getAll() {
        return areaModel.getAll();
    }

    @Override
    public Area getById(int id) throws AreaException {
        return areaModel.getById(id);
    }

    @Override
    public List<User> getAvalibleUsers(Area area) throws AreaException {
        return userModel.getUsersNotWithArea(area);
    }

    @Override
    public void add(Area area) {
        areaModel.add(area);
    }

    @Override
    public void update(Area area) throws AreaException {
        areaModel.update(area);
    }

    @Override
    public void delete(int id) throws AreaException {
        areaModel.delete(id);
    }
}
