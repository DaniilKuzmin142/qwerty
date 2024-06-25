package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.checkConnection();
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 24);
        userService.saveUser("Олег", "Олегов", (byte) 27);
        userService.saveUser("Никита", "Никитов", (byte) 30);
        userService.saveUser("Александр", "Александров", (byte) 33);

        for(User user: userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.createUsersTable();
        userService.dropUsersTable();

    }
}
