package org.example;

public class CalculationService {

    private final CalculationRepository repository;

    public CalculationService(CalculationRepository repository) {
        this.repository = repository;
    }

    public void saveCalculation(double distance, double consumption, double price,
                                double totalFuel, double totalCost, String language) {

        if (DatabaseConnection.testmode) {
            return;
        }

        repository.save(distance, consumption, price, totalFuel, totalCost, language);
    }
}
