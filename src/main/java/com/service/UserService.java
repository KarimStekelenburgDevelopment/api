package com.service;

import java.util.List;

import com.entity.User;
import com.exception.LoginException;
import com.model.UserModel;
import com.model.UserModelInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserModelInterface userModel = new UserModel();



    public List<User> getAll(){
        return userModel.getAll();
    }
    @Transactional
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
    public User getById(int id) {
        User obj = userModel.getById(id);
        return obj;
    }

    @Override
    public User getUserByUsername(String username) throws LoginException {
        User obj = userModel.getUserByUsername(username);
        return obj;
    }



    @Override
    public void update(User user) {
        userModel.update(user);
    }

    @Override
    public void delete(int userId) {
        userModel.delete(userId);
    }

    @Override
    public boolean validatePassword(User user, String password){ return userModel.validatePassword(user, password); }
}