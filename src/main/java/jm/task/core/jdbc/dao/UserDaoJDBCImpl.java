package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table if not exists Users(id bigint not null auto_increment, Name varchar(30), LastName varchar(30), Age TINYINT(3), primary key (id))");
    }

    public void dropUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("drop table if exists Users");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Users (Name, LastName, Age) values (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from Users where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = null;
        try {
            list = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                list.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = Util.getConnection().createStatement();
        statement.execute("Truncate table Users");
    }
}
