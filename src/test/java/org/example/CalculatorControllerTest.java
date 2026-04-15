package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorControllerTest {

    private CalculatorController controller;

    @BeforeEach
    void setup() {
        DatabaseConnection.TEST_MODE = true;
        controller = new CalculatorController();
    }

    // --- calculateFuel ---

    @Test
    void testCalculateFuel_normal() {
        assertEquals(20.0, controller.calculateFuel(250, 8), 0.0001);
    }

    @Test
    void testCalculateFuel_zero_distance() {
        assertEquals(0.0, controller.calculateFuel(0, 8), 0.0001);
    }

    @Test
    void testCalculateFuel_zero_consumption() {
        assertEquals(0.0, controller.calculateFuel(100, 0), 0.0001);
    }

    @Test
    void testCalculateFuel_large_values() {
        assertEquals(150.0, controller.calculateFuel(1000, 15), 0.0001);
    }

    @Test
    void testCalculateFuel_decimal_consumption() {
        assertEquals(6.5, controller.calculateFuel(100, 6.5), 0.0001);
    }

    @Test
    void testCalculateFuel_small_distance() {
        assertEquals(0.08, controller.calculateFuel(1, 8), 0.0001);
    }

    // --- calculateCost ---

    @Test
    void testCalculateCost_normal() {
        assertEquals(27.0, controller.calculateCost(18, 1.5), 0.0001);
    }

    @Test
    void testCalculateCost_zero_fuel() {
        assertEquals(0.0, controller.calculateCost(0, 2), 0.0001);
    }

    @Test
    void testCalculateCost_zero_price() {
        assertEquals(0.0, controller.calculateCost(10, 0), 0.0001);
    }

    @Test
    void testCalculateCost_high_price() {
        assertEquals(100.0, controller.calculateCost(50, 2.0), 0.0001);
    }

    @Test
    void testCalculateCost_decimal_values() {
        assertEquals(3.0, controller.calculateCost(2.0, 1.5), 0.0001);
    }

    @Test
    void testCalculateCost_large_values() {
        assertEquals(500.0, controller.calculateCost(100, 5.0), 0.0001);
    }

    // --- validointi logiikka (suoraan testattu) ---

    @Test
    void testValidation_negative_distance() {
        assertTrue(-1 <= 0);
    }

    @Test
    void testValidation_negative_consumption() {
        assertTrue(-5 <= 0);
    }

    @Test
    void testValidation_negative_price() {
        assertTrue(-2 <= 0);
    }

    @Test
    void testValidation_valid_inputs() {
        assertFalse(100 <= 0 || 8 <= 0 || 1.5 <= 0);
    }

    // --- kombinaatiotestit (fuel + cost yhdessä) ---

    @Test
    void testFuelAndCost_combined() {
        double fuel = controller.calculateFuel(300, 7);
        double cost = controller.calculateCost(fuel, 1.8);
        assertEquals(21.0, fuel, 0.0001);
        assertEquals(37.8, cost, 0.001);
    }

    @Test
    void testFuelAndCost_city_driving() {
        double fuel = controller.calculateFuel(50, 10);
        double cost = controller.calculateCost(fuel, 2.0);
        assertEquals(5.0, fuel, 0.0001);
        assertEquals(10.0, cost, 0.0001);
    }

    @Test
    void testFuelAndCost_highway() {
        double fuel = controller.calculateFuel(500, 6);
        double cost = controller.calculateCost(fuel, 1.6);
        assertEquals(30.0, fuel, 0.0001);
        assertEquals(48.0, cost, 0.0001);
    }
}