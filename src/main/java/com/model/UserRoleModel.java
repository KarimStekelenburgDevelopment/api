package com.model;

import com.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRoleModel implements UserRoleModelInterface {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserRole> getAll() {
        String hql = "FROM UserRole as usrRole ORDER BY usrRole.id";
        return (List<UserRole >) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public UserRole getById(int id) {
        return entityManager.find(UserRole.class, id);
    }

    @Override
    public void add(UserRole role) {
        entityManager.persist(role);
    }

    @Override
    public void update(UserRole role) {
        UserRole roleFromDb = getById(role.getId());
        roleFromDb.setName(role.getName());
        roleFromDb.setUsers(role.getUsers());
        entityManager.flush();
    }

    @Override
    public void delete(int id) {
        entityManager.remove(getById(id));
    }
}
