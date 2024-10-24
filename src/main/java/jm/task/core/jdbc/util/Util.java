package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    public static String url = "jdbc:mysql://localhost:3306/users";
    public static String user = "tilt";
    public static  String passwd = "tilt";

    private Util (){

    }

    public static Connection getNewConnection()  {
        try {
            return DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
