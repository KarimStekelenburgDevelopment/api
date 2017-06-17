package com.controller;

import com.entity.Area;
import com.entity.Table;
import com.entity.User;
import com.exception.AreaException;
import com.exception.UserException;
import com.service.AreaService;
import com.service.AreaServiceInterface;
import com.service.UserServiceInterface;
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
@RequestMapping("area")
public class AreaController {

    @Autowired
    private AreaServiceInterface areaService;

    @Autowired
    private UserServiceInterface userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Area>> getAllUsers(@RequestHeader("Authorization") String token) throws UnsupportedEncodingException {
        List<Area> list = areaService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Area> getById(@RequestHeader("Authorization") String token , @PathVariable("id") int id) throws UnsupportedEncodingException, AreaException {
        Area area = areaService.getById(id);
        return new ResponseEntity<Area>(area, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "getTables/{id}")
    public ResponseEntity<List<Table>> getTables(@RequestHeader("Authorization") String token , @PathVariable("id") int id) throws UnsupportedEncodingException, AreaException {
        Area area = areaService.getById(id);
        return new ResponseEntity<List<Table>>(area.getTables(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "getUsers/{id}")
    public ResponseEntity<List<User>> getUsers(@RequestHeader("Authorization") String token , @PathVariable("id") int id) throws AreaException {
        Area area = areaService.getById(id);
        return new ResponseEntity<List<User>>(area.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "getAvalibleUsers/{id}")
    public ResponseEntity<List<User>> getAvalibleUsers(@RequestHeader("Authorization") String token , @PathVariable("id") int id) throws AreaException {
        Area area = areaService.getById(id);

        return new ResponseEntity<List<User>>(areaService.getAvalibleUsers(area), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addArea(@RequestBody Area area, UriComponentsBuilder builder) {
        areaService.add(area);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/area/{id}").buildAndExpand(area.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addUser/{areaId}")
    public ResponseEntity<Void> addUser
            (@RequestHeader("Authorization") String token,
             @RequestBody User user,
             @PathVariable("areaId") int areaId,
             UriComponentsBuilder builder
            )
            throws AreaException, UserException
    {
        Area area = areaService.getById(areaId);
        User user1 = userService.getById(user.getId());
        area.addUser(user1);
        areaService.update(area);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/area/{id}").buildAndExpand(area.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Area> updateUser(@RequestBody Area area) throws AreaException {
        areaService.update(area);
        return new ResponseEntity<Area>(area, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) throws AreaException {
        areaService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeUser/{areaId}")
    public ResponseEntity<Void> removeUser
            (@RequestHeader("Authorization") String token,
             @RequestBody User user,
             @PathVariable("areaId") int areaId,
             UriComponentsBuilder builder
            )
            throws AreaException, UserException
    {
        Area area = areaService.getById(areaId);
        User user1 = userService.getById(user.getId());
        area.removeUser(user1);
        areaService.update(area);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/area/{id}").buildAndExpand(area.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }



}
