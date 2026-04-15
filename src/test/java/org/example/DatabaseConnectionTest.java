package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @BeforeEach
    void setup() {
        DatabaseConnection.TESTMODE = true;
    }

    @AfterEach
    void tearDown() {
        DatabaseConnection.TESTMODE = true;
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
        assertTrue(DatabaseConnection.TESTMODE);
    }

    @Test
    void testTestMode_canBeToggled() {
        DatabaseConnection.TESTMODE = true;
        assertTrue(DatabaseConnection.TESTMODE);
        DatabaseConnection.TESTMODE = false;
        assertFalse(DatabaseConnection.TESTMODE);
        DatabaseConnection.TESTMODE = true; // palautetaan
    }

    @Test
    void testGetConnection_testMode_multipleCalls() {
        // Varmistetaan että jokainen kutsu heittää poikkeuksen TESTMODEssa
        assertThrows(SQLException.class, DatabaseConnection::getConnection);
        assertThrows(SQLException.class, DatabaseConnection::getConnection);
        assertThrows(SQLException.class, DatabaseConnection::getConnection);
    }
}
