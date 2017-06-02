package com;

import com.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        configureUsingHibernatePropertiesFile();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();


        session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            User dbEmployee = (User) session.get(User.class, 1);
            System.out.println(dbEmployee.getId() + " - " + dbEmployee.getUsername());

            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }


    public static void configureUsingHibernatePropertiesFile() throws IOException {
        // Create configuration instance
        Configuration configuration = new Configuration();

        // Create properties file
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        // Pass hibernate properties file
        configuration.setProperties(properties);
        // Since version 4.x, service registry is being used
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();

        // Create session factory instance
        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

        // Get current session
        Session session = factory.getCurrentSession();


        try {
            session.beginTransaction();

            User dbEmployee = (User) session.get(User.class, 1);
            System.out.println(dbEmployee.getId() + " - " + dbEmployee.getUsername());

            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }


        // Print out all required information
        System.out.println("Session Is Opened :: "+session.isOpen());
        System.out.println("Session Is Connected :: "+session.isConnected());

        // Commit transaction
        session.getTransaction().commit();

    }
}
