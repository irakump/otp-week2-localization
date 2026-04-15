package org.example;

public interface CalculationRepository {
    void save(double distance, double consumption, double price,
              double totalFuel, double totalCost, String language);
}
