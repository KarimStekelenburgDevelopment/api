package com.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository
public class UserModel implements UserModelInterface {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<User > getAll() {
        String hql = "FROM User as usr ORDER BY usr.id";
        return (List<User >) entityManager.createQuery(hql).getResultList();
    }
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }
    @Override
    public void update(User user) {
        User user1 = getById(user.getId());
        user1.setUsername(user.getUsername());
        user1.setPsswrd(user.getPsswrd());
        user1.setAreas(user.getAreas());
        user1.setRole(user.getRole());
        entityManager.flush();
    }
    @Override
    public void delete(int id) {
        entityManager.remove(getById(id));
    }

    @Override
    public boolean exists(String username) {
        String hql = "FROM User as usr WHERE usr.username = ?";
        Object user = entityManager.createQuery(hql).setParameter(0, username).getSingleResult();
        return user != null;
    }
}