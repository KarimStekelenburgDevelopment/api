package com.model;

import com.entity.User;
import com.entity.UserRole;
import com.exception.LoginException;
import com.exception.UserException;
import com.exception.UserRoleException;
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll() {
        String hql = "FROM User as usr ORDER BY usr.id";
        return (List<User>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public User getById(int id) throws UserException {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new UserException("USER_NOT_FOUND: user not found");
        }
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) throws UserException {
        User user;
        System.out.println(username);
        try {
            user = entityManager.createQuery(
                    "FROM User WHERE username = :username", User.class).
                    setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            throw new UserException("USER_NOT_FOUND: user not found");
        }
        return user;


    }

    @Transactional
    @Override
    public void add(User user) throws UserRoleException, UserException {
        try{
            if (exists(user.getUsername())) {
                System.out.println("username bestaat");
                throw new UserException("USERNAME_TAKEN: username is already taken");

            }
            if (user.getRole() != null){
                System.out.println("rolezoekings");
                UserRole userRole = userRoleModel.getById(user.getRole().getId());
                user.setRole(userRole);
            }
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            System.out.println(user);
            entityManager.merge(user);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new UserRoleException("ROLE_NOT_FOUND: role not found");
        }
    }
    /**
     * persists an update on a user object to the database.
     * be aware that FULL INFORMATION needs to be provided due to
     * password enctryption.
     *
     * @param user
     */
    @Transactional
    @Override
    public void update(User user) throws UserException {
        System.out.println(user);
        User user1 = getById(user.getId());
        if (user.getUsername() != null){
            user1.setUsername(user.getUsername());
        } if (user.getPassword() != null){
            user1.setPassword(passwordEncoder().encode(user.getPassword()));
        } if (user.getAreas() != null){
            user1.setAreas(user.getAreas());
        } if (user.getRole() != null){
            user1.setRole(user.getRole());
        }
        entityManager.merge(user1);
    }

    @Transactional
    @Override
    public void delete(int id) throws UserException {
        entityManager.remove(getById(id));
    }

    @Override
    public boolean exists(String username) {
        String hql = "FROM User as usr WHERE usr.username = ?";
        try{
            User user = (User) entityManager.createQuery(hql).setParameter(0, username).getSingleResult();
            return true;
        } catch (Exception e){
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validatePassword(User user, String password) {
        String userInput = passwordEncoder().encode(password);
        return passwordEncoder().matches(password, user.getPassword());
    }
}