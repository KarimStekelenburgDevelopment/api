package com.model;

import com.entity.User;
import com.entity.UserRole;
import com.exception.LoginException;
import com.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


@Transactional
@Repository
public class UserModel implements UserModelInterface {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleModel userRoleModel;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User > getAll() {
        String hql = "FROM User as usr ORDER BY usr.id";
        return (List<User >) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) throws LoginException {
        User user;

        try{
            user = entityManager.createQuery(
                    "SELECT u from User u WHERE u.username = :username", User.class).
                    setParameter("username", username).getSingleResult();
        }
        catch (NoResultException e){
            throw new LoginException("user not found");
        }
        return user;



    }


    @Override
    public void add(User user) {
        System.out.println(userRoleModel.getById(1));
        UserRole userRole = userRoleModel.getById(user.getRole().getId());
        user.setRole(userRole);
        System.out.println(user);
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        entityManager.persist(user);
    }

    /**
     * persists an update on a user object to the database.
     * be aware that FULL INFORMATION needs to be provided due to
     * password enctryption.
     * @param user
     */

    @Override
    public void update(User user) {
        User user1 = getById(user.getId());
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder().encode(user.getPassword()));
        user1.setAreas(user.getAreas());
        user1.setRole(user.getRole());
        entityManager.flush();
    }
    @Override
    public void delete(int id) {
        entityManager.remove(getById(id));
    }

    @Override
    public boolean exists(String username){
        String hql = "FROM User as usr WHERE usr.username = ?";
        try{
            entityManager.createQuery(hql).setParameter(0, username).getSingleResult();
        }catch (NoResultException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean validatePassword(User user, String password){
        String userInput = passwordEncoder().encode(password);
        return passwordEncoder().matches(password, user.getPassword());
    }
}