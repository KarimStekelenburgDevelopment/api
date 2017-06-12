package com.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.entity.User;
import com.exception.UserException;
import com.exception.UserRoleException;
import com.service.UserServiceInterface;
import com.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceInterface userService;

    @Autowired
    JWTUtil jwtUtil;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token) throws UnsupportedEncodingException, UserException {
        

        List<User> list = userService.getAll();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) throws UserException {
        User user = userService.getById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder builder) throws UserRoleException, UserException {
        System.out.println("controller");
        userService.add(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserException {
        userService.update(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) throws UserException {
        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}