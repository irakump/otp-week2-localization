package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @BeforeEach
    void setup() {
        DatabaseConnection.testmode = true;
    }

    @AfterEach
    void tearDown() {
        DatabaseConnection.testmode = true;
    }

    @Test
    void testGetConnection_testMode_throwsSQLException() {
        assertThrows(SQLException.class, DatabaseConnection::getConnection
        );
    }

    @Test
    void testGetConnection_testMode_exceptionMessage() {
        SQLException ex = assertThrows(SQLException.class, DatabaseConnection::getConnection
        );
        assertNotNull(ex.getMessage());
        assertTrue(ex.getMessage().contains("Test mode"));
    }

    @Test
    void testTestMode_defaultIsTrue() {
        // Varmistetaan että TEST_MODE on päällä setupin jälkeen
        assertTrue(DatabaseConnection.testmode);
    }

    @Test
    void testTestMode_canBeToggled() {
        DatabaseConnection.testmode = true;
        assertTrue(DatabaseConnection.testmode);
        DatabaseConnection.testmode = false;
        assertFalse(DatabaseConnection.testmode);
        DatabaseConnection.testmode = true; // palautetaan
    }

    @Test
    void testGetConnection_testMode_multipleCalls() {
        // Varmistetaan että jokainen kutsu heittää poikkeuksen TESTMODEssa
        assertThrows(SQLException.class, DatabaseConnection::getConnection);
        assertThrows(SQLException.class, DatabaseConnection::getConnection);
        assertThrows(SQLException.class, DatabaseConnection::getConnection);
    }
}
