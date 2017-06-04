package com.controller;

import com.entity.User;
import com.service.UserServiceInterface;
import com.util.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("login")
public class LoginController {
    private UserServiceInterface userService;
    private JWTUtil jwtUtil = new JWTUtil();

    /**
     * Handles login requests by validating the user exists and returning a generated JWT-token
     * for request authorization.
     * @param request the request data
     * @return an error if the user is unknown, a JWT-token if all goes well.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> requestLogin(HttpServletRequest request) {
        User user = userService.getUserByUsername(request.getParameter("username"));
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LOGIN FAILED");
        }
        return ResponseEntity.status(HttpStatus.OK).body("{token : "+jwtUtil.generateJWT(user)+" }");
    }
}
