package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @BeforeEach
    void setup() {
        DatabaseConnection.TEST_MODE = true;
    }

    @AfterEach
    void tearDown() {
        DatabaseConnection.TEST_MODE = true; // varmistetaan että jää test modeen
    }

    @Test
    void testGetConnection_testMode_throwsSQLException() {
        assertThrows(SQLException.class, () ->
                DatabaseConnection.getConnection()
        );
    }

    @Test
    void testGetConnection_testMode_exceptionMessage() {
        SQLException ex = assertThrows(SQLException.class, () ->
                DatabaseConnection.getConnection()
        );
        assertNotNull(ex.getMessage());
        assertTrue(ex.getMessage().contains("Test mode"));
    }

    @Test
    void testTestMode_defaultIsTrue() {
        // Varmistetaan että TEST_MODE on päällä setupin jälkeen
        assertTrue(DatabaseConnection.TEST_MODE);
    }

    @Test
    void testTestMode_canBeToggled() {
        DatabaseConnection.TEST_MODE = true;
        assertTrue(DatabaseConnection.TEST_MODE);
        DatabaseConnection.TEST_MODE = false;
        assertFalse(DatabaseConnection.TEST_MODE);
        DatabaseConnection.TEST_MODE = true; // palautetaan
    }

    @Test
    void testGetConnection_testMode_multipleCalls() {
        // Varmistetaan että jokainen kutsu heittää poikkeuksen TEST_MODEssa
        assertThrows(SQLException.class, () -> DatabaseConnection.getConnection());
        assertThrows(SQLException.class, () -> DatabaseConnection.getConnection());
        assertThrows(SQLException.class, () -> DatabaseConnection.getConnection());
    }
}
