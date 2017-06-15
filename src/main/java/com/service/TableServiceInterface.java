package com.service;

import com.entity.Table;
import com.exception.AreaException;
import com.exception.TableException;

import java.util.List;

public interface TableServiceInterface {
    List<Table> getAll();
    Table getById(int id) throws TableException;

    List<Table> getTablesByAreaId(int id) throws TableException, AreaException;

    void add(Table table);

    void update(Table table) throws TableException;
    void delete(int id) throws TableException;
}
