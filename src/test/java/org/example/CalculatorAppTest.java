package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorAppTest {

    @BeforeEach
    void setup() {
        DatabaseConnection.testmode = true;
    }

    @Test
    void testAppClassExists() {
        assertDoesNotThrow(CalculatorApp::new);
    }
}
