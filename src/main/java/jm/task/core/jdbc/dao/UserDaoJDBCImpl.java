package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getNewConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection newConnection = getNewConnection(); Statement statement = newConnection.createStatement()) {
            String SQL = "CREATE TABLE IF NOT EXISTS Users"
                    + "(id INT PRIMARY KEY AUTO_INCREMENT, "
                    + " name VARCHAR(20), "
                    + " lastName VARCHAR (20), "
                    + " age TINYINT)";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection newConnection = getNewConnection(); Statement statement = newConnection.createStatement()) {
            String SQL = "DROP TABLE IF EXISTS Users";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection newConnection = getNewConnection();
             PreparedStatement preparedStatement = newConnection.prepareStatement("INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?) ");) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем - %s добавлен в базу данных.\n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection newConnection = getNewConnection();
             PreparedStatement preparedStatement = newConnection.prepareStatement("DELETE FROM Users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection newConnection = getNewConnection();
             Statement statement = newConnection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Users")) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection newConnection = getNewConnection(); PreparedStatement preparedStatement = newConnection.prepareStatement("TRUNCATE TABLE Users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
