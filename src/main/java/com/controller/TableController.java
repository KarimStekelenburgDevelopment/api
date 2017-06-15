package com.controller;

import com.entity.Table;
import com.exception.AreaException;
import com.exception.TableException;
import com.service.TableServiceInterface;
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
@RequestMapping("table")
public class TableController {

    @Autowired
    private TableServiceInterface tableService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Table>> getAllUsers(@RequestHeader("Authorization") String token) throws UnsupportedEncodingException {
        List<Table> list = tableService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Table> getById(@RequestHeader("Authorization") String token, @PathVariable("id") int id) throws TableException {
        Table table = tableService.getById(id);
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/forArea/{id}")
    public ResponseEntity<List<Table>> getTablesByAreaId(@RequestHeader("Authorization") String token, @PathVariable("id") int id) throws TableException, AreaException {
        List<Table> tables = tableService.getTablesByAreaId(id);
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addTable(@RequestBody Table table, UriComponentsBuilder builder) {
        tableService.add(table);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/table/{id}").buildAndExpand(table.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Table> updateUser(@RequestBody Table table) throws TableException {
        tableService.update(table);
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) throws TableException {
        tableService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
