package com.controller;

import com.entity.User;
import com.entity.UserRole;
import com.service.UserRoleServiceInterface;
import com.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("role")
public class UserRoleController {

    @Autowired
    private UserRoleServiceInterface userRoleService = new UserRoleService();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserRole>> getAllRoles(@RequestHeader("Authorization") String token) throws UnsupportedEncodingException {
        List<UserRole> list = userRoleService.getAll();
        return new ResponseEntity<List<UserRole>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRole> getRoleById(@PathVariable("id") Integer id) {
        UserRole role = userRoleService.getById(id);
        return new ResponseEntity<UserRole>(role, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addRole(@RequestBody UserRole role, UriComponentsBuilder builder) {
        boolean flag = userRoleService.add(role);
        if (flag == false) {
            System.out.println("soidsjdkjdlkhfj");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/role/{id}").buildAndExpand(role.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UserRole> updateRole(@RequestBody UserRole role) {
        userRoleService.update(role);
        return new ResponseEntity<UserRole>(role, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Integer id) {
        userRoleService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
