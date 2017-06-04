package com.controller;

import com.entity.LoginRequest;
import com.entity.User;
import com.exception.LoginException;
import com.service.UserService;
import com.service.UserServiceInterface;
import com.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServiceInterface userService = new UserService();
    private JWTUtil jwtUtil = new JWTUtil();

    /**
     * Handles login requests by validating the user exists and returning a generated JWT-token
     * for request authorization.
     * @param username the request data
     * @return an error if the user is unknown, a JWT-token if all goes well.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> requestLogin(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername());
        User user = null;
        try {
            user = userService.getUserByUsername(loginRequest.getUsername());
        } catch (LoginException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LOGIN FAILED");
        }
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LOGIN FAILED");
        }
        return ResponseEntity.status(HttpStatus.OK).body("{token : "+jwtUtil.generateJWT(user)+" }");
    }
}
