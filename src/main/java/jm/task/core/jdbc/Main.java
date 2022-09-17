package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("George", "Kiladze", (byte) 25);
        userService.saveUser("Rezo", "Kiladze", (byte) 23);
        userService.saveUser("Lesin", "Vitalik", (byte) 20);
        userService.saveUser("Romanov", "Nikita", (byte) 25);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
