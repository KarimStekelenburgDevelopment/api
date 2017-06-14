package com.service;

import java.util.List;

import com.entity.User;
import com.exception.UserException;
import com.exception.UserRoleException;
import com.model.UserModel;
import com.model.UserModelInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserModelInterface userModel = new UserModel();


    @Override
    public List<User> getAll(){
        return userModel.getAll();
    }

    @Transactional
    @Override
    public void add(User user) throws UserRoleException, UserException {
        userModel.add(user);
    }

    @Override
    public User getById(int id) throws UserException {
        User obj = userModel.getById(id);
        return obj;
    }

    @Override
    public User getUserByUsername(String username) throws UserException {
        User obj = userModel.getUserByUsername(username);
        return obj;
    }


    @Transactional
    @Override
    public void update(User user) throws UserException {
        userModel.update(user);
    }

    @Transactional
    @Override
    public void delete(int userId) throws UserException {
        userModel.delete(userId);
    }

    @Override
    public boolean validatePassword(User user, String password){ return userModel.validatePassword(user, password); }
}