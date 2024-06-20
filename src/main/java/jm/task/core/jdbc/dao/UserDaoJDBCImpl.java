package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users  (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "lastName VARCHAR(50), " +
                    "age SMALLINT)");

        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users ");


        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection= Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (name,lastName,age) VALUES (?,?,?)")){
       preparedStatement.setString(1,name);
       preparedStatement.setString(2, lastName);
       preparedStatement.setByte(3,age);
       preparedStatement.executeUpdate();
       System.out.println("Пользователь с именем -- " + name + " добавлен в базу");
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users  WHERE id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users ")){
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Connection connection= Util.getConnection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM Users ");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
