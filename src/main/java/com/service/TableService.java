package com.service;

import com.entity.Table;
import com.exception.AreaException;
import com.exception.TableException;
import com.model.TableModel;
import com.model.TableModelInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TableService implements TableServiceInterface {
    @Autowired
    private TableModelInterface tableModel;


    @Override
    public List<Table> getAll() {
        return tableModel.getAll();
    }

    @Override
    public Table getById(int id) throws TableException {
        return tableModel.getById(id);
    }

    @Override
    public List<Table> getTablesByAreaId(int id) throws TableException, AreaException {
        return tableModel.getTablesByAreaId(id);
    }

    @Override
    public void add(Table table){
        tableModel.add(table);
    }

    @Override
    public void update(Table table) throws TableException {
        tableModel.update(table);
    }

    @Override
    public void delete(int id) throws TableException {
        tableModel.delete(id);
    }
}
