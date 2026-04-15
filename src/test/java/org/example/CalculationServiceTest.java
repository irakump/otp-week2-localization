package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {

    @BeforeEach
    void setup() {
        DatabaseConnection.TEST_MODE = true;
    }

    @Test
    void testSaveCalculation_testMode_doesNotThrow() {
        // TEST_MODE=true -> pitää skipata DB ja ei heittää poikkeusta
        assertDoesNotThrow(() ->
                CalculationService.saveCalculation(100, 8, 1.5, 8.0, 12.0, "en")
        );
    }

    @Test
    void testSaveCalculation_withZeroValues() {
        assertDoesNotThrow(() ->
                CalculationService.saveCalculation(0, 0, 0, 0, 0, "fi")
        );
    }

    @Test
    void testSaveCalculation_withFrenchLanguage() {
        assertDoesNotThrow(() ->
                CalculationService.saveCalculation(200, 7, 1.8, 14.0, 25.2, "fr")
        );
    }

    @Test
    void testSaveCalculation_withJapaneseLanguage() {
        assertDoesNotThrow(() ->
                CalculationService.saveCalculation(500, 6, 2.0, 30.0, 60.0, "ja")
        );
    }

    @Test
    void testSaveCalculation_withNegativeValues() {
        // Negatiiviset arvot — service ei validoi, vain tallentaa (tai test modessa skipaa)
        assertDoesNotThrow(() ->
                CalculationService.saveCalculation(-100, -5, -1, -5, -5, "en")
        );
    }

    @Test
    void testSaveCalculation_withLargeValues() {
        assertDoesNotThrow(() ->
                CalculationService.saveCalculation(99999, 15, 3.5, 14999.85, 52499.475, "en")
        );
    }
}
