package com.model;

import com.entity.Area;
import com.entity.Table;
import com.exception.AreaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class AreaModel implements AreaModelInterface {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Area> getAll() {
        return entityManager.createNamedQuery("getAllAreas").getResultList();
    }

    @Override
    public Area getById(int id) throws AreaException {
        Area area = entityManager.find(Area.class, id);
        if (area == null){
            throw new AreaException("AREA_NOT_FOUND");
        }
        return area;
    }

    @Transactional
    @Override
    public void add(Area area) {
        entityManager.merge(area);
    }

    @Transactional
    @Override
    public void update(Area area) throws AreaException {
        Area area1 = getById(area.getId());
        if (area.getName() != null){
            area1.setName(area.getName());
        } if (area.getTables() != null){
            area1.setTables(area.getTables());
        } if (area.getUsers() != null){
            area1.setTables(area.getTables());
        }
        entityManager.merge(area1);
    }

    @Transactional
    @Override
    public void delete(int id) throws AreaException {
        entityManager.remove(getById(id));
    }
}
