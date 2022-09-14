package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/home";
        String us = "root";
        String password = "1234";
        try {
            Connection connection = DriverManager.getConnection(url, us, password);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getFactory() {
        Properties property = new Properties();
        property.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/home");
        property.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        property.setProperty("connection.driver_class", "com.mysql.cj.jdbc.Driver");
        property.setProperty("connection.username", "root");
        property.setProperty("hibernate.connection.password", "1234");
        property.setProperty("hibernate.current_session_context_class", "thread");
        property.setProperty("show_sql", "true");
        property.setProperty("hibernate.hbm2ddl.auto", "update");

        SessionFactory factory = new Configuration()
                .addProperties(property)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        return factory;
    }
    // реализуйте настройку соеденения с БД
}
