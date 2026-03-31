package org.example;
import java.sql.*;

public class CalculationService {

    private static final String DB_NAME = "fuel_calculator_localization";
    private static final String DB_USER = "test";
    private static final String DB_PASSWORD = "Test12";

    // Load MariaDB driver
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String getDatabaseHost() {
        String host = System.getenv("DB_HOST");
        if (host == null || host.isEmpty()) host = "localhost"; // use Docker service name
        return host;
    }

    private static String getDatabaseUrl() {
        return "jdbc:mariadb://" + getDatabaseHost() + ":3307/" + DB_NAME +
                "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    }

    private static Connection getConnection(String dbUrl) throws SQLException {
        return DriverManager.getConnection(dbUrl, DB_USER, DB_PASSWORD);
    }

    public static void saveCalculation(double distance, double consumption, double price, double totalFuel, double totalCost, String language) {
        String dbUrl = getDatabaseUrl();

        try (Connection conn = getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {

            // Create table if it doesn't exist
            String createTable = """
                CREATE TABLE IF NOT EXISTS calculation_results (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    distance DOUBLE NOT NULL,
                    consumption DOUBLE NOT NULL,
                    price DOUBLE NOT NULL,
                    total_fuel DOUBLE NOT NULL,
                    total_cost DOUBLE NOT NULL,
                    language VARCHAR(10),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;
            stmt.executeUpdate(createTable);

            // Insert the result
            String insert = "INSERT INTO calc_results (distance, consumption, price, totalFuel, totalCost, language) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insert)) {
                ps.setDouble(1, distance);
                ps.setDouble(2, consumption);
                ps.setDouble(3, price);
                ps.setDouble(4, totalFuel);
                ps.setDouble(5, totalCost);
                ps.setString(6, language);
                ps.executeUpdate();
            }

            System.out.println("Result saved: dist " + distance + ", consumption + " + consumption + ", price " + price +
                    ", totalFuel " + totalFuel + ", totalCost" + totalCost + ", lang " + language);

        } catch (SQLException e) {
            System.err.println("Failed to save result to DB: " + dbUrl);
            e.printStackTrace();
        }
    }
}
