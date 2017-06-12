package com.service;

import com.entity.UserRole;
import com.model.UserRoleModel;
import com.model.UserRoleModelInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleService implements UserRoleServiceInterface {
    @Autowired
    private UserRoleModelInterface userRoleModel = new UserRoleModel();

    @Override
    public List<UserRole> getAll() {
        return userRoleModel.getAll();
    }

    @Override
    public UserRole getById(int id) {
        return userRoleModel.getById(id);
    }



    @Transactional
    @Override
    public synchronized boolean add(UserRole role){
        if (userRoleModel.getById(role.getId()) != null) {
            return false;
        } else {
            userRoleModel.add(role);
            return true;
        }
    }

    @Override
    public void update(UserRole role) {
        userRoleModel.update(role);
    }

    @Override
    public void delete(int id) {
        userRoleModel.delete(id);
    }
}
