package com.controller;

import com.entity.Area;
import com.entity.Table;
import com.exception.AreaException;
import com.service.AreaService;
import com.service.AreaServiceInterface;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addArea(@RequestBody Area area, UriComponentsBuilder builder) {
        areaService.add(area);
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
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, path = "getTables/{id}")
    public ResponseEntity<List<Table>> getTables(@RequestHeader("Authorization") String token , @PathVariable("id") int id) throws UnsupportedEncodingException, AreaException {
        Area area = areaService.getById(id);
        return new ResponseEntity<List<Table>>(area.getTables(), HttpStatus.OK);
    }
}
