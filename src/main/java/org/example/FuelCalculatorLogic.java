package org.example;

public class FuelCalculatorLogic {

    private FuelCalculatorLogic() {}

    public static double calculateFuel(double distance, double consumption) {
        return (consumption / 100.0) * distance;
    }

    public static double calculateCost(double fuel, double price) {
        return fuel * price;
    }

    public static boolean isValidInput(double distance, double consumption, double price) {
        return distance > 0 && consumption > 0 && price > 0;
    }

    public static double calculateTotalCost(double distance, double consumption, double price) {
        double fuel = calculateFuel(distance, consumption);
        return calculateCost(fuel, price);
    }

    public static String formatResult(double value) {
        return String.format(java.util.Locale.US, "%.2f", value);
    }

    public static double roundToTwo(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static double fuelEfficiencyRating(double consumption) {
        if (consumption < 4) return 5.0;
        else if (consumption < 6) return 4.0;
        else if (consumption < 8) return 3.0;
        else if (consumption < 10) return 2.0;
        else return 1.0;
    }

    public static String tripCategory(double distance) {
        if (distance < 10) return "local";
        else if (distance < 100) return "short";
        else if (distance < 500) return "medium";
        else return "long";
    }

    public static double estimatedTime(double distance, double speedKmh) {
        if (speedKmh <= 0) return 0;
        return distance / speedKmh;
    }

    public static boolean isFuelEconomical(double consumption) {
        return consumption < 6.0;
    }

    public static double co2Emission(double fuelLiters) {
        return fuelLiters * 2.31;
    }

    public static double litersPer100km(double totalFuel, double distance) {
        if (distance <= 0) return 0;
        return (totalFuel / distance) * 100;
    }

    public static double kmPerLiter(double consumption) {
        if (consumption <= 0) return 0;
        return 100.0 / consumption;
    }

    public static double totalDistance(double fuelAvailable, double consumption) {
        if (consumption <= 0) return 0;
        return (fuelAvailable / consumption) * 100;
    }

    public static String costCategory(double cost) {
        if (cost < 20) return "cheap";
        else if (cost < 60) return "moderate";
        else return "expensive";
    }

    public static double savingsVsAverage(double yourConsumption, double avgConsumption, double distance, double price) {
        double yourCost = calculateTotalCost(distance, yourConsumption, price);
        double avgCost = calculateTotalCost(distance, avgConsumption, price);
        return avgCost - yourCost;
    }

    public static int refuelStops(double distance, double tankRange) {
        if (tankRange <= 0) return 0;
        return (int) Math.floor(distance / tankRange);
    }

    public static double monthlyFuelCost(double dailyDistance, double consumption, double price, int days) {
        double dailyCost = calculateTotalCost(dailyDistance, consumption, price);
        return dailyCost * days;
    }

    public static double fuelCostPerKm(double consumption, double price) {
        return (consumption / 100.0) * price;
    }

    public static double annualFuelCost(double dailyDistance, double consumption, double price) {
        return monthlyFuelCost(dailyDistance, consumption, price, 365);
    }

    public static boolean needsRefuel(double remainingFuel, double distance, double consumption) {
        double needed = calculateFuel(distance, consumption);
        return remainingFuel < needed;
    }

    public static double remainingRangeKm(double remainingFuel, double consumption) {
        if (consumption <= 0) return 0;
        return (remainingFuel / consumption) * 100;
    }

    public static double percentageFuelUsed(double startFuel, double remainingFuel) {
        if (startFuel <= 0) return 0;
        return ((startFuel - remainingFuel) / startFuel) * 100;
    }

    public static String fuelLevelStatus(double percent) {
        if (percent >= 75) return "full";
        else if (percent >= 50) return "half";
        else if (percent >= 25) return "low";
        else return "critical";
    }

    public static double convertLitersToCubicMeters(double liters) {
        return liters / 1000.0;
    }

    public static double convertKmToMiles(double km) {
        return km * 0.621371;
    }

    public static double convertMilesToKm(double miles) {
        return miles * 1.60934;
    }

    public static double convertGallonsToLiters(double gallons) {
        return gallons * 3.78541;
    }

    public static double convertLitersToGallons(double liters) {
        return liters / 3.78541;
    }

    public static double mpgToL100km(double mpg) {
        if (mpg <= 0) return 0;
        return 235.214 / mpg;
    }

    public static double l100kmToMpg(double l100km) {
        if (l100km <= 0) return 0;
        return 235.214 / l100km;
    }

    public static double averageConsumption(double trip1Fuel, double trip1Dist,
                                            double trip2Fuel, double trip2Dist) {
        double totalDist = trip1Dist + trip2Dist;
        if (totalDist <= 0) return 0;
        return ((trip1Fuel + trip2Fuel) / totalDist) * 100;
    }

    public static int estimateRefuelFrequency(double weeklyDistance, double tankRange) {
        if (tankRange <= 0) return 0;
        return (int) Math.ceil(weeklyDistance / tankRange);
    }

    public static double breakEvenDistance(double fixedCost, double costPerKm) {
        if (costPerKm <= 0) return 0;
        return fixedCost / costPerKm;
    }

    public static double taxAmount(double cost, double taxRate) {
        return cost * (taxRate / 100.0);
    }

    public static double costWithTax(double cost, double taxRate) {
        return cost + taxAmount(cost, taxRate);
    }

    public static boolean isTripWorthDriving(double distance, double threshold) {
        return distance >= threshold;
    }

    public static double fuelBudgetRemaining(double budget, double spent) {
        return Math.max(0, budget - spent);
    }

    public static int daysUntilEmpty(double remainingFuel, double dailyConsumptionLiters) {
        if (dailyConsumptionLiters <= 0) return 0;
        return (int) Math.floor(remainingFuel / dailyConsumptionLiters);
    }

    public static double roundToDecimals(double value, int decimals) {
        double factor = Math.pow(10, decimals);
        return Math.round(value * factor) / factor;
    }

    public static boolean isLongTrip(double distance) {
        return distance > 300;
    }

    public static boolean isCheapFuel(double price) {
        return price < 1.5;
    }

    public static double fuelSavedBySpeedReduction(double distance, double highConsumption, double lowConsumption) {
        return calculateFuel(distance, highConsumption) - calculateFuel(distance, lowConsumption);
    }

    public static double percentageSaved(double original, double discounted) {
        if (original <= 0) return 0;
        return ((original - discounted) / original) * 100;
    }

    public static double maxAffordableDistance(double budget, double consumption, double price) {
        double costPerKm = fuelCostPerKm(consumption, price);
        if (costPerKm <= 0) return 0;
        return budget / costPerKm;
    }

    public static double averageSpeed(double distance, double timeHours) {
        if (timeHours <= 0) return 0;
        return distance / timeHours;
    }

    public static double fuelPerMinute(double consumption, double speedKmh) {
        if (speedKmh <= 0) return 0;
        return (consumption / 100.0) * (speedKmh / 60.0);
    }

    public static boolean canReachDestination(double fuelLiters, double distance, double consumption) {
        return fuelLiters >= calculateFuel(distance, consumption);
    }

    public static double surplusFuel(double fuelLiters, double distance, double consumption) {
        return fuelLiters - calculateFuel(distance, consumption);
    }

    public static double costDifference(double cost1, double cost2) {
        return Math.abs(cost1 - cost2);
    }

    public static int fullTankRefuels(double totalDistance, double tankRangeKm) {
        if (tankRangeKm <= 0) return 0;
        return (int) Math.ceil(totalDistance / tankRangeKm) - 1;
    }
}
