package org.example;

import java.sql.*;

public class JdbcCalculationRepository implements CalculationRepository {

    @Override
    public void save(double distance, double consumption, double price,
                     double totalFuel, double totalCost, String language) {

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
