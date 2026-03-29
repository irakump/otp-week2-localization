package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorControllerTest {

    private final CalculatorController controller = new CalculatorController();

    @Test
    void testCalculateFuel() {
        double fuel = controller.calculateFuel(250, 8); // 200 km, 8 L/100 km
        assertEquals(20.0, fuel, 0.0001);
    }

    @Test
    void testCalculateCost() {
        double fuel = 18.0;
        double cost = controller.calculateCost(fuel, 1.5); // 1.5 per L
        assertEquals(27.0, cost, 0.0001);
    }
}