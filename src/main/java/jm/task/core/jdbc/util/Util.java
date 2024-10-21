package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static String url = "jdbc:mysql://localhost:3306/users";
    public static String user = "tilt";
    public static  String passwd = "tilt";

    private Util (){

    }

    public static Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(url, user, passwd);
    }

}
