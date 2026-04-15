package org.example;

import java.sql.*;

public class CalculationService {

    private CalculationService() {

    }

    // Load MariaDB driver
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveCalculation(double distance, double consumption, double price,
                                       double totalFuel, double totalCost, String language) {

        if (DatabaseConnection.TEST_MODE) {
            System.out.println("TEST MODE: skipping DB save");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create table if it doesn't exist
            String createTable = """
                CREATE TABLE IF NOT EXISTS calculation_records (
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
            String insert = """
                INSERT INTO calculation_records
                (distance, consumption, price, total_fuel, total_cost, language)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

            try (PreparedStatement ps = conn.prepareStatement(insert)) {
                ps.setDouble(1, distance);
                ps.setDouble(2, consumption);
                ps.setDouble(3, price);
                ps.setDouble(4, totalFuel);
                ps.setDouble(5, totalCost);
                ps.setString(6, language);
                ps.executeUpdate();
            }

            System.out.println("Saved: dist=" + distance + " fuel=" + totalFuel + " cost=" + totalCost + " lang=" + language);

        } catch (SQLException e) {
            System.err.println("Failed to save result to DB");
            e.printStackTrace();
        }
    }
}
