package com.model;

import com.entity.Area;
import com.entity.Table;
import com.exception.AreaException;
import com.exception.TableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import java.util.List;




@Repository
@Transactional
public class TableModel implements TableModelInterface{


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AreaModelInterface areaModel;

    @Override
    public List<Table> getAll() {
        return (List<Table>) entityManager.createNamedQuery("getAllTables").getResultList();
    }

    @Override
    public Table getById(int id) throws TableException {
        Table table = entityManager.find(Table.class, id);
        if (table == null){
            throw new TableException("TABLE_NOT_FOUND");
        }
        return table;
    }

    @Override
    public List<Table> getTablesByAreaId(int id) throws TableException, AreaException {
        return (List<Table>) entityManager
                .createNamedQuery("getByAreaId")
                .setParameter("area", areaModel.getById(id))
                .getResultList();
    }

    @Override
    public List<Table> getTablesByArea(Area area) throws TableException {
        return (List<Table>) entityManager
                .createNamedQuery("getByAreaId")
                .setParameter("area", area)
                .getResultList();
    }

    @Transactional
    @Override
    public void add(Table table){
        entityManager.merge(table);
    }

    @Override
    public void update(Table table) throws TableException {
        Table table1 = getById(table.getId());
        if (table.getArea() != null){
            table1.setArea(table.getArea());
        } if ((Integer) table.getNumofseats() != null){
            table1.setNumofseats(table.getNumofseats());
        }if (table.getOrders() != null){
            table1.setOrders(table.getOrders());
        }
        entityManager.merge(table1);
    }

    @Override
    public void delete(int id) throws TableException {
        entityManager.remove(getById(id));
    }
}
