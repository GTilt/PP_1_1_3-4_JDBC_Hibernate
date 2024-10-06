package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();
        try {
            userDao.createUsersTable();
            userDao.saveUser("Sergey", "Pershin", (byte) 22);
            userDao.saveUser("Ilon", "Mask", (byte)30);
            userDao.saveUser("Steve", "Jobs", (byte)67);
            userDao.saveUser("Ana", "D'Armas", (byte)28);
            userDao.getAllUsers().toString();
            userDao.cleanUsersTable();
            userDao.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
