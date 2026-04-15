package org.example;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {

    CalculationRepository repository;
    CalculationService service;

    @BeforeEach
    void setup() {
        repository = mock(CalculationRepository.class);
        service = new CalculationService(repository);
    }

    @Test
    void testSave_ok() {
        assertDoesNotThrow(() ->
                service.saveCalculation(100, 8, 1.5, 8, 12, "en")
        );

        verify(repository, times(1))
                .save(100, 8, 1.5, 8, 12, "en");
    }


}
