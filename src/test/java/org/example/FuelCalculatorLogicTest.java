package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuelCalculatorLogicTest {

    // --- calculateFuel ---
    @Test void fuel_normal()           { assertEquals(20.0,  FuelCalculatorLogic.calculateFuel(250, 8),   0.0001); }
    @Test void fuel_zero_distance()    { assertEquals(0.0,   FuelCalculatorLogic.calculateFuel(0, 8),     0.0001); }
    @Test void fuel_zero_consumption() { assertEquals(0.0,   FuelCalculatorLogic.calculateFuel(100, 0),   0.0001); }
    @Test void fuel_large()            { assertEquals(150.0, FuelCalculatorLogic.calculateFuel(1000, 15), 0.0001); }
    @Test void fuel_decimal()          { assertEquals(6.5,   FuelCalculatorLogic.calculateFuel(100, 6.5), 0.0001); }
    @Test void fuel_one_km()           { assertEquals(0.08,  FuelCalculatorLogic.calculateFuel(1, 8),     0.0001); }
    @Test void fuel_highway()          { assertEquals(30.0,  FuelCalculatorLogic.calculateFuel(500, 6),   0.0001); }

    // --- calculateCost ---
    @Test void cost_normal()      { assertEquals(27.0,  FuelCalculatorLogic.calculateCost(18, 1.5),  0.0001); }
    @Test void cost_zero_fuel()   { assertEquals(0.0,   FuelCalculatorLogic.calculateCost(0, 2),     0.0001); }
    @Test void cost_zero_price()  { assertEquals(0.0,   FuelCalculatorLogic.calculateCost(10, 0),    0.0001); }
    @Test void cost_high_price()  { assertEquals(100.0, FuelCalculatorLogic.calculateCost(50, 2.0),  0.0001); }
    @Test void cost_large()       { assertEquals(500.0, FuelCalculatorLogic.calculateCost(100, 5.0), 0.0001); }
    @Test void cost_decimal()     { assertEquals(3.0,   FuelCalculatorLogic.calculateCost(2.0, 1.5), 0.0001); }

    // --- isValidInput ---
    @Test void valid_all_positive()      { assertTrue(FuelCalculatorLogic.isValidInput(100, 8, 1.5));  }
    @Test void invalid_zero_distance()   { assertFalse(FuelCalculatorLogic.isValidInput(0, 8, 1.5));   }
    @Test void invalid_zero_consumption(){ assertFalse(FuelCalculatorLogic.isValidInput(100, 0, 1.5)); }
    @Test void invalid_zero_price()      { assertFalse(FuelCalculatorLogic.isValidInput(100, 8, 0));   }
    @Test void invalid_negative_dist()   { assertFalse(FuelCalculatorLogic.isValidInput(-1, 8, 1.5));  }
    @Test void invalid_negative_cons()   { assertFalse(FuelCalculatorLogic.isValidInput(100, -5, 1.5));}
    @Test void invalid_negative_price()  { assertFalse(FuelCalculatorLogic.isValidInput(100, 8, -1));  }
    @Test void invalid_all_zero()        { assertFalse(FuelCalculatorLogic.isValidInput(0, 0, 0));      }

    // --- calculateTotalCost ---
    @Test void total_normal()  { assertEquals(37.8,  FuelCalculatorLogic.calculateTotalCost(300, 7, 1.8),   0.001); }
    @Test void total_city()    { assertEquals(10.0,  FuelCalculatorLogic.calculateTotalCost(50, 10, 2.0),   0.0001); }
    @Test void total_highway() { assertEquals(48.0,  FuelCalculatorLogic.calculateTotalCost(500, 6, 1.6),   0.0001); }
    @Test void total_zero()    { assertEquals(0.0,   FuelCalculatorLogic.calculateTotalCost(0, 8, 1.5),     0.0001); }
    @Test void total_large() { assertEquals(525.0, FuelCalculatorLogic.calculateTotalCost(5000, 7, 1.5), 0.001); }

    // --- formatResult ---
    @Test void format_two_decimals()  { assertEquals("20.00", FuelCalculatorLogic.formatResult(20.0));    }
    @Test void format_decimal()       { assertEquals("18.50", FuelCalculatorLogic.formatResult(18.5));    }
    @Test void format_zero()          { assertEquals("0.00",  FuelCalculatorLogic.formatResult(0.0));     }
    @Test void format_many_decimals() { assertEquals("3.33",  FuelCalculatorLogic.formatResult(3.333));   }

    // --- roundToTwo ---
    @Test void round_normal()    { assertEquals(3.33,  FuelCalculatorLogic.roundToTwo(3.333),  0.0001); }
    @Test void round_up()        { assertEquals(3.34,  FuelCalculatorLogic.roundToTwo(3.335),  0.0001); }
    @Test void round_zero()      { assertEquals(0.0,   FuelCalculatorLogic.roundToTwo(0.0),    0.0001); }
    @Test void round_whole()     { assertEquals(5.0,   FuelCalculatorLogic.roundToTwo(5.0),    0.0001); }
    @Test void round_negative()  { assertEquals(-1.23, FuelCalculatorLogic.roundToTwo(-1.234), 0.0001); }

    // --- fuelEfficiencyRating ---
    @Test void rating_excellent()  { assertEquals(5.0, FuelCalculatorLogic.fuelEfficiencyRating(3.5), 0.0001); }
    @Test void rating_good()       { assertEquals(4.0, FuelCalculatorLogic.fuelEfficiencyRating(5.0), 0.0001); }
    @Test void rating_average()    { assertEquals(3.0, FuelCalculatorLogic.fuelEfficiencyRating(7.0), 0.0001); }
    @Test void rating_poor()       { assertEquals(2.0, FuelCalculatorLogic.fuelEfficiencyRating(9.0), 0.0001); }
    @Test void rating_bad()        { assertEquals(1.0, FuelCalculatorLogic.fuelEfficiencyRating(12.0), 0.0001); }

    // --- tripCategory ---
    @Test void trip_local()   { assertEquals("local",  FuelCalculatorLogic.tripCategory(5));   }
    @Test void trip_short()   { assertEquals("short",  FuelCalculatorLogic.tripCategory(50));  }
    @Test void trip_medium()  { assertEquals("medium", FuelCalculatorLogic.tripCategory(200)); }
    @Test void trip_long()    { assertEquals("long",   FuelCalculatorLogic.tripCategory(600)); }

    // --- estimatedTime ---
    @Test void time_normal()      { assertEquals(2.0, FuelCalculatorLogic.estimatedTime(200, 100), 0.0001); }
    @Test void time_zero_speed()  { assertEquals(0.0, FuelCalculatorLogic.estimatedTime(100, 0),   0.0001); }
    @Test void time_zero_dist()   { assertEquals(0.0, FuelCalculatorLogic.estimatedTime(0, 100),   0.0001); }

    // --- isFuelEconomical ---
    @Test void economical_true()  { assertTrue(FuelCalculatorLogic.isFuelEconomical(5.9));  }
    @Test void economical_false() { assertFalse(FuelCalculatorLogic.isFuelEconomical(6.0)); }
    @Test void economical_low()   { assertTrue(FuelCalculatorLogic.isFuelEconomical(3.0));  }

    // --- co2Emission ---
    @Test void co2_normal()  { assertEquals(23.1,  FuelCalculatorLogic.co2Emission(10),  0.001); }
    @Test void co2_zero()    { assertEquals(0.0,   FuelCalculatorLogic.co2Emission(0),   0.001); }
    @Test void co2_large()   { assertEquals(231.0, FuelCalculatorLogic.co2Emission(100), 0.001); }

    // --- litersPer100km ---
    @Test void lp100_normal()     { assertEquals(10.0, FuelCalculatorLogic.litersPer100km(10, 100), 0.0001); }
    @Test void lp100_zero_dist()  { assertEquals(0.0,  FuelCalculatorLogic.litersPer100km(10, 0),   0.0001); }
    @Test void lp100_half()       { assertEquals(5.0,  FuelCalculatorLogic.litersPer100km(5, 100),  0.0001); }

    // --- kmPerLiter ---
    @Test void kpl_normal()      { assertEquals(12.5, FuelCalculatorLogic.kmPerLiter(8),  0.0001); }
    @Test void kpl_zero()        { assertEquals(0.0,  FuelCalculatorLogic.kmPerLiter(0),  0.0001); }
    @Test void kpl_efficient()   { assertEquals(25.0, FuelCalculatorLogic.kmPerLiter(4),  0.0001); }

    // --- totalDistance ---
    @Test void dist_normal()     { assertEquals(500.0, FuelCalculatorLogic.totalDistance(40, 8),  0.0001); }
    @Test void dist_zero_cons()  { assertEquals(0.0,   FuelCalculatorLogic.totalDistance(40, 0),  0.0001); }
    @Test void dist_small_tank() { assertEquals(100.0, FuelCalculatorLogic.totalDistance(6, 6),   0.0001); }

    // --- costCategory ---
    @Test void cat_cheap()      { assertEquals("cheap",     FuelCalculatorLogic.costCategory(15));  }
    @Test void cat_moderate()   { assertEquals("moderate",  FuelCalculatorLogic.costCategory(40));  }
    @Test void cat_expensive()  { assertEquals("expensive", FuelCalculatorLogic.costCategory(80));  }

    // --- savingsVsAverage ---
    @Test void savings_positive() { assertTrue(FuelCalculatorLogic.savingsVsAverage(5, 8, 100, 1.5) > 0);  }
    @Test void savings_negative() { assertTrue(FuelCalculatorLogic.savingsVsAverage(10, 6, 100, 1.5) < 0); }
    @Test void savings_equal()    { assertEquals(0.0, FuelCalculatorLogic.savingsVsAverage(7, 7, 100, 1.5), 0.0001); }

    // --- refuelStops ---
    @Test void stops_none()   { assertEquals(0, FuelCalculatorLogic.refuelStops(100, 500)); }
    @Test void stops_one()    { assertEquals(1, FuelCalculatorLogic.refuelStops(600, 500)); }
    @Test void stops_zero_range() { assertEquals(0, FuelCalculatorLogic.refuelStops(100, 0)); }

    // --- monthlyFuelCost ---
    @Test void monthly_normal() { assertEquals(336.0, FuelCalculatorLogic.monthlyFuelCost(50, 8, 1.4, 60), 0.001); }
    @Test void monthly_zero()   { assertEquals(0.0,   FuelCalculatorLogic.monthlyFuelCost(0, 8, 1.4, 30),  0.001); }

    // --- fuelCostPerKm ---
    @Test void cpkm_normal()  { assertEquals(0.12, FuelCalculatorLogic.fuelCostPerKm(8, 1.5), 0.0001); }
    @Test void cpkm_zero()    { assertEquals(0.0,  FuelCalculatorLogic.fuelCostPerKm(0, 1.5), 0.0001); }

    // --- annualFuelCost ---
    @Test void annual_normal() { assertTrue(FuelCalculatorLogic.annualFuelCost(50, 8, 1.5) > 0); }
    @Test void annual_zero()   { assertEquals(0.0, FuelCalculatorLogic.annualFuelCost(0, 8, 1.5), 0.001); }

    // --- needsRefuel ---
    @Test void refuel_yes()  { assertTrue(FuelCalculatorLogic.needsRefuel(5, 200, 8));   }
    @Test void refuel_no()   { assertFalse(FuelCalculatorLogic.needsRefuel(50, 100, 8)); }

    // --- remainingRangeKm ---
    @Test void range_normal()    { assertEquals(375.0, FuelCalculatorLogic.remainingRangeKm(30, 8),  0.0001); }
    @Test void range_zero_cons() { assertEquals(0.0,   FuelCalculatorLogic.remainingRangeKm(30, 0),  0.0001); }
    @Test void range_empty()     { assertEquals(0.0,   FuelCalculatorLogic.remainingRangeKm(0, 8),   0.0001); }

    // --- percentageFuelUsed ---
    @Test void pct_half()      { assertEquals(50.0, FuelCalculatorLogic.percentageFuelUsed(60, 30), 0.0001); }
    @Test void pct_zero_start(){ assertEquals(0.0,  FuelCalculatorLogic.percentageFuelUsed(0, 30),  0.0001); }
    @Test void pct_full()      { assertEquals(100.0,FuelCalculatorLogic.percentageFuelUsed(60, 0),  0.0001); }

    // --- fuelLevelStatus ---
    @Test void status_full()     { assertEquals("full",     FuelCalculatorLogic.fuelLevelStatus(80));  }
    @Test void status_half()     { assertEquals("half",     FuelCalculatorLogic.fuelLevelStatus(60));  }
    @Test void status_low()      { assertEquals("low",      FuelCalculatorLogic.fuelLevelStatus(30));  }
    @Test void status_critical() { assertEquals("critical", FuelCalculatorLogic.fuelLevelStatus(10));  }

    // --- conversions ---
    @Test void liters_to_m3()     { assertEquals(0.05,  FuelCalculatorLogic.convertLitersToCubicMeters(50),   0.0001); }
    @Test void km_to_miles()      { assertEquals(62.1371, FuelCalculatorLogic.convertKmToMiles(100),          0.001);  }
    @Test void miles_to_km()      { assertEquals(160.934, FuelCalculatorLogic.convertMilesToKm(100),          0.001);  }
    @Test void gallons_to_liters() { assertEquals(37.8541, FuelCalculatorLogic.convertGallonsToLiters(10),    0.001);  }
    @Test void liters_to_gallons() { assertEquals(2.6417,  FuelCalculatorLogic.convertLitersToGallons(10),    0.001);  }

    // --- mpg / l100km ---
    @Test void mpg_to_l100()    { assertEquals(7.84,  FuelCalculatorLogic.mpgToL100km(30),   0.01); }
    @Test void mpg_zero()       { assertEquals(0.0,   FuelCalculatorLogic.mpgToL100km(0),    0.0001); }
    @Test void l100_to_mpg()    { assertEquals(39.2,  FuelCalculatorLogic.l100kmToMpg(6),    0.1);   }
    @Test void l100_zero()      { assertEquals(0.0,   FuelCalculatorLogic.l100kmToMpg(0),    0.0001); }

    // --- averageConsumption ---
    @Test void avg_cons_normal() { assertEquals(12.5, FuelCalculatorLogic.averageConsumption(15, 100, 22.5, 200), 0.0001); }
    @Test void avg_cons_zero()   { assertEquals(0.0, FuelCalculatorLogic.averageConsumption(10, 0, 10, 0),       0.0001); }

    // --- estimateRefuelFrequency ---
    @Test void freq_once()  { assertEquals(1, FuelCalculatorLogic.estimateRefuelFrequency(300, 500)); }
    @Test void freq_twice() { assertEquals(2, FuelCalculatorLogic.estimateRefuelFrequency(600, 400)); }
    @Test void freq_zero()  { assertEquals(0, FuelCalculatorLogic.estimateRefuelFrequency(300, 0));   }

    // --- breakEvenDistance ---
    @Test void breakeven_normal() { assertEquals(500.0, FuelCalculatorLogic.breakEvenDistance(100, 0.2), 0.0001); }
    @Test void breakeven_zero()   { assertEquals(0.0,   FuelCalculatorLogic.breakEvenDistance(100, 0),   0.0001); }

    // --- taxAmount / costWithTax ---
    @Test void tax_amount()    { assertEquals(5.0,  FuelCalculatorLogic.taxAmount(50, 10),    0.0001); }
    @Test void tax_zero()      { assertEquals(0.0,  FuelCalculatorLogic.taxAmount(50, 0),     0.0001); }
    @Test void with_tax()      { assertEquals(55.0, FuelCalculatorLogic.costWithTax(50, 10),  0.0001); }

    // --- isTripWorthDriving ---
    @Test void worth_yes() { assertTrue(FuelCalculatorLogic.isTripWorthDriving(100, 50));  }
    @Test void worth_no()  { assertFalse(FuelCalculatorLogic.isTripWorthDriving(30, 50));  }

    // --- fuelBudgetRemaining ---
    @Test void budget_remaining()  { assertEquals(30.0, FuelCalculatorLogic.fuelBudgetRemaining(100, 70), 0.0001); }
    @Test void budget_overspent()  { assertEquals(0.0,  FuelCalculatorLogic.fuelBudgetRemaining(50, 80),  0.0001); }

    // --- daysUntilEmpty ---
    @Test void days_normal() { assertEquals(5, FuelCalculatorLogic.daysUntilEmpty(50, 10)); }
    @Test void days_zero()   { assertEquals(0, FuelCalculatorLogic.daysUntilEmpty(50, 0));  }

    // --- roundToDecimals ---
    @Test void round_dec_2()   { assertEquals(3.14,  FuelCalculatorLogic.roundToDecimals(3.14159, 2), 0.0001); }
    @Test void round_dec_0()   { assertEquals(3.0,   FuelCalculatorLogic.roundToDecimals(3.14159, 0), 0.0001); }
    @Test void round_dec_1()   { assertEquals(3.1,   FuelCalculatorLogic.roundToDecimals(3.14159, 1), 0.0001); }

    // --- isLongTrip ---
    @Test void long_trip_yes() { assertTrue(FuelCalculatorLogic.isLongTrip(400));  }
    @Test void long_trip_no()  { assertFalse(FuelCalculatorLogic.isLongTrip(200)); }
    @Test void long_trip_edge(){ assertFalse(FuelCalculatorLogic.isLongTrip(300)); }

    // --- isCheapFuel ---
    @Test void cheap_yes()  { assertTrue(FuelCalculatorLogic.isCheapFuel(1.2));  }
    @Test void cheap_no()   { assertFalse(FuelCalculatorLogic.isCheapFuel(1.8)); }
    @Test void cheap_edge() { assertFalse(FuelCalculatorLogic.isCheapFuel(1.5)); }

    // --- fuelSavedBySpeedReduction ---
    @Test void fuel_saved_normal() { assertEquals(6.0, FuelCalculatorLogic.fuelSavedBySpeedReduction(200, 10, 7), 0.0001); }
    @Test void fuel_saved_zero()   { assertEquals(0.0, FuelCalculatorLogic.fuelSavedBySpeedReduction(0, 10, 7),   0.0001); }

    // --- percentageSaved ---
    @Test void pct_saved_normal() { assertEquals(20.0, FuelCalculatorLogic.percentageSaved(100, 80), 0.0001); }
    @Test void pct_saved_zero()   { assertEquals(0.0,  FuelCalculatorLogic.percentageSaved(0, 80),   0.0001); }
    @Test void pct_saved_none()   { assertEquals(0.0,  FuelCalculatorLogic.percentageSaved(80, 80),  0.0001); }

    // --- maxAffordableDistance ---
    @Test void max_dist_normal() { assertEquals(625.0, FuelCalculatorLogic.maxAffordableDistance(75, 8, 1.5), 0.001); }
    @Test void max_dist_zero()   { assertEquals(0.0,   FuelCalculatorLogic.maxAffordableDistance(75, 0, 1.5), 0.001); }

    // --- averageSpeed ---
    @Test void speed_normal()    { assertEquals(100.0, FuelCalculatorLogic.averageSpeed(200, 2),   0.0001); }
    @Test void speed_zero_time() { assertEquals(0.0,   FuelCalculatorLogic.averageSpeed(200, 0),   0.0001); }
    @Test void speed_zero_dist() { assertEquals(0.0,   FuelCalculatorLogic.averageSpeed(0, 2),     0.0001); }

    // --- fuelPerMinute ---
    @Test void fpm_normal()    { assertEquals(0.1333, FuelCalculatorLogic.fuelPerMinute(8, 100), 0.001); }
    @Test void fpm_zero_speed(){ assertEquals(0.0,    FuelCalculatorLogic.fuelPerMinute(8, 0),   0.0001); }

    // --- canReachDestination ---
    @Test void reach_yes()  { assertTrue(FuelCalculatorLogic.canReachDestination(50, 200, 8));  }
    @Test void reach_no()   { assertFalse(FuelCalculatorLogic.canReachDestination(5, 200, 8));  }
    @Test void reach_exact(){ assertTrue(FuelCalculatorLogic.canReachDestination(16, 200, 8));  }

    // --- surplusFuel ---
    @Test void surplus_positive() { assertEquals(34.0, FuelCalculatorLogic.surplusFuel(50, 200, 8),  0.0001); }
    @Test void surplus_negative() { assertEquals(-11.0,FuelCalculatorLogic.surplusFuel(5, 200, 8),   0.0001); }
    @Test void surplus_zero()     { assertEquals(0.0,  FuelCalculatorLogic.surplusFuel(16, 200, 8),  0.0001); }

    // --- costDifference ---
    @Test void diff_normal()   { assertEquals(10.0, FuelCalculatorLogic.costDifference(50, 40), 0.0001); }
    @Test void diff_reversed() { assertEquals(10.0, FuelCalculatorLogic.costDifference(40, 50), 0.0001); }
    @Test void diff_zero()     { assertEquals(0.0,  FuelCalculatorLogic.costDifference(50, 50), 0.0001); }

    // --- fullTankRefuels ---
    @Test void refuels_none()  { assertEquals(0, FuelCalculatorLogic.fullTankRefuels(400, 500)); }
    @Test void refuels_one()   { assertEquals(1, FuelCalculatorLogic.fullTankRefuels(600, 400)); }
    @Test void refuels_zero_range() { assertEquals(0, FuelCalculatorLogic.fullTankRefuels(500, 0)); }
}
