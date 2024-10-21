package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.Configuration;

public class Util {
    // реализуйте настройку соеденения с БД

    public static String url = "jdbc:mysql://localhost:3306/users";
    public static String user = "tilt";
    public static  String passwd = "tilt";

    private static SessionFactory sessionFactory;
    private Util (){

    }

    public static Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(url, user, passwd);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try{
                Configuration configuration = getConfiguration();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
            }
        }
        return sessionFactory;
    }

    public static void shutdown(){
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users");
        configuration.setProperty("hibernate.connection.username", "tilt");
        configuration.setProperty("hibernate.connection.password", "tilt");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    public static void execute(String sql) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
