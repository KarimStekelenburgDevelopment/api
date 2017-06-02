package com.controller;

import com.entity.User;
import com.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserServiceInterface userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> requestLogin(HttpServletRequest request) {
        User user = userService.getUserByUsername(request.getParameter("username"));

        


        return new ResponseEntity<String>(user, HttpStatus.OK);
    }
}
