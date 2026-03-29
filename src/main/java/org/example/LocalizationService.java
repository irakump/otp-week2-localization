package org.example;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LocalizationService {

    /**
     * Get localized strings for a specific locale
     */
    public static Map<String, String> getLocalizedStrings(Locale locale) {
        Map<String, String> strings = new HashMap<>();

        try {
            // Load ResourceBundle from root of resources
            ResourceBundle bundle = ResourceBundle.getBundle(
                    "MessageBundle", // base name matches file: MessageBundle_xx_YY.properties
                    locale
            );

            for (String key : bundle.keySet()) {
                strings.put(key, bundle.getString(key));
            }

        } catch (Exception e) {
            System.err.println("Failed to load resource bundle for locale: " + locale);

            // fallback to English
            try {
                ResourceBundle fallback = ResourceBundle.getBundle(
                        "MessageBundle",
                        new Locale("en", "US")
                );
                for (String key : fallback.keySet()) {
                    strings.put(key, fallback.getString(key));
                }
            } catch (Exception ex) {
                // Last resort: hardcoded defaults
                strings.put("title", "Fuel & Trip Cost Calculator");
                strings.put("distance.label", "Distance (km)");
                strings.put("consumption.label", "Fuel Consumption (L/100 km)");
                strings.put("price.label", "Fuel Price (per liter)");
                strings.put("calculate.button", "Calculate");
                strings.put("result.label", "Total fuel needed: {0} L | Total cost: {1}");
                strings.put("invalid.input", "Invalid input");
            }
        }

        return strings;
    }
}
