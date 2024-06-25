package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соединения с БД
    private static SessionFactory sessionFactory;
    static {
        try{
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            settings.put("hibernate.connection.url","jdbc:postgresql://localhost:5432/postgres");
            settings.put("hibernate.connection.username","postgres");
            settings.put("hibernate.connection.password","0000");
            settings.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
            settings.put("hibernate.hbm2ddl.auto","update");
            settings.put("hibernate.show_sql","true");
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory= configuration.buildSessionFactory(serviceRegistry);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
public static SessionFactory getSessionFactory(){
        return sessionFactory;
}









    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";

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
            connection = DriverManager.getConnection(URL, USER, Password);
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