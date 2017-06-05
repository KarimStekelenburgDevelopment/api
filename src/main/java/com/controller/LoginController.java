package com.controller;

import com.entity.LoginRequest;
import com.entity.User;
import com.exception.LoginException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.service.UserService;
import com.service.UserServiceInterface;
import com.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServiceInterface userService = new UserService();
    private JWTUtil jwtUtil = new JWTUtil();
    private Gson gson = new GsonBuilder().create();

    public LoginController() throws IOException {
    }


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

            if (userService.validatePassword(user, loginRequest.getPassword()) == false){
                throw new LoginException("password incorrect");
            }


        } catch (LoginException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login failure");
        }
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("token", jwtUtil.generateJWT(user));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
