package com.model;

import com.entity.Area;
import com.entity.Table;
import com.exception.AreaException;
import com.exception.TableException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TableModelInterface {
    List<Table> getAll();
    Table getById(int id) throws TableException;

    List<Table> getTablesByAreaId(int id) throws TableException, AreaException;

    List<Table> getTablesByArea(Area area) throws TableException;

    void add(Table table);

    void update(Table table) throws TableException;

    void delete(int id) throws TableException;
}
