package com.service;

import java.util.List;

import com.entity.User;
import com.model.UserModelInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserModelInterface userModel;
    @Override
    public User getById(int id) {
        User obj = userModel.getById(id);
        return obj;
    }
    @Override
    public List<User> getAll(){
        return userModel.getAll();
    }
    @Override
    public synchronized boolean add(User user){
        if (userModel.exists(user.getUsername())) {
            return false;
        } else {
            userModel.add(user);
            return true;
        }
    }
    @Override
    public void update(User user) {
        userModel.update(user);
    }
    @Override
    public void delete(int userId) {
        userModel.delete(userId);
    }
}