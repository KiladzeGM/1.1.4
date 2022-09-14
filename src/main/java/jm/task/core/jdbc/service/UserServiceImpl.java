package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoJDBCImpl();
    public void createUsersTable() throws SQLException {
        UserDao userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        UserDao userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        UserDao userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("User с именем – '" + name +"' добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        UserDao userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        UserDao userDaoHibernate = new UserDaoHibernateImpl();
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        UserDao userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.cleanUsersTable();
    }
}
