package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String HOST = System.getenv().getOrDefault("DB_HOST", "localhost");
    private static final String PORT = System.getenv().getOrDefault("DB_PORT", "3306");
    private static final String DB_NAME = "fuel_calculator_localization";
    private static final String URL = "jdbc:mariadb://" + HOST + ":" + PORT + "/" + DB_NAME
            + "?useUnicode=true&characterEncoding=UTF-8";

    private static final String USER = System.getenv().getOrDefault("DB_USER", "test");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "Test123");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
