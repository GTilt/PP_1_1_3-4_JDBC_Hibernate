package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Sergey", "Pershin", (byte) 22);
        userService.saveUser("Ilon", "Mask", (byte) 30);
        userService.saveUser("Steve", "Jobs", (byte) 67);
        userService.saveUser("Ana", "D'Armas", (byte) 28);
        userService.getAllUsers();
        /*userService.cleanUsersTable();*/
        /*userService.dropUsersTable();*/
    }
}


