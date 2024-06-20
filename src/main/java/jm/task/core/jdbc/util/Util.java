package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String User = "postgres";

    private static final String Password = "0000";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, User, Password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void checkConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connection is successful!");
            } else {
                System.err.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при проверке соединения: " + e.getMessage());
            e.printStackTrace();
        }
    }
}