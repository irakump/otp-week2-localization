package org.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.Map;

public class CalculatorController {

    @FXML private VBox rootVBox;
    @FXML private Label lblTitle;
    @FXML private Label lblDistance;
    @FXML private Label lblConsumption;
    @FXML private Label lblPrice;
    @FXML private TextField txtDistance;
    @FXML private TextField txtConsumption;
    @FXML private TextField txtPrice;
    @FXML private Button btnCalculate;
    @FXML private Button btnEN, btnFR, btnJP, btnIR;
    @FXML private Label lblResult;
    @FXML private Label lblCost;

    private Locale currentLocale = new Locale("en", "US");
    private Map<String, String> localizedStrings;

    @FXML
    public void initialize() {
        // Set default language
        setLanguage(currentLocale);

        // Clear result when any input changes
        txtDistance.textProperty().addListener((obs, oldVal, newVal) -> {
            lblResult.setText("");
            lblCost.setText("");
        });
        txtConsumption.textProperty().addListener((obs, oldVal, newVal) -> {
            lblResult.setText("");
            lblCost.setText("");
        });
        txtPrice.textProperty().addListener((obs, oldVal, newVal) -> {
            lblResult.setText("");
            lblCost.setText("");
        });
    }

    /** Language button handlers **/
    @FXML public void onENClick(ActionEvent e) { setLanguage(new Locale("en", "US")); }
    @FXML public void onFRClick(ActionEvent e) { setLanguage(new Locale("fr", "FR")); }
    @FXML public void onJAClick(ActionEvent e) { setLanguage(new Locale("ja", "JP")); }
    @FXML public void onFAClick(ActionEvent e) { setLanguage(new Locale("fa", "IR")); }

    /** Calculate fuel and trip cost **/
    @FXML
    public void onCalculateClick(ActionEvent e) {
        try {
            double distance = Double.parseDouble(txtDistance.getText());
            double consumption = Double.parseDouble(txtConsumption.getText());
            double price = Double.parseDouble(txtPrice.getText());

            if (distance <= 0 || consumption <= 0 || price <= 0) {
                lblResult.setText(localizedStrings.getOrDefault("invalid.input", "Invalid input"));
                lblCost.setText("");
                return;
            }

            double totalFuel = (consumption / 100.0) * distance;
            double totalCost = totalFuel * price;

            String result = String.format(
                    localizedStrings.getOrDefault("result.label",
                            "Total fuel needed: %.2f L"),
                    totalFuel
            );

            String costResult = String.format(
                    localizedStrings.getOrDefault("cost.label", "Total cost: %.2f"),
                    totalCost
            );

            lblResult.setText(result);
            lblCost.setText(costResult);

        } catch (NumberFormatException ex) {
            lblResult.setText(localizedStrings.getOrDefault("invalid.input", "Invalid input"));
            lblCost.setText("");
        }
    }

    /** Set application language and update UI **/
    private void setLanguage(Locale locale) {
        currentLocale = locale;
        lblResult.setText("");
        lblCost.setText("");

        // Load localized strings
        localizedStrings = LocalizationService.getLocalizedStrings(locale);

        // Update UI text
        lblTitle.setText(localizedStrings.getOrDefault("title", "Fuel & Trip Cost Calculator"));
        lblDistance.setText(localizedStrings.getOrDefault("distance.label", "Distance (km)"));
        lblConsumption.setText(localizedStrings.getOrDefault("consumption.label", "Fuel Consumption (L/100 km)"));
        lblPrice.setText(localizedStrings.getOrDefault("price.label", "Fuel Price (per liter)"));
        btnCalculate.setText(localizedStrings.getOrDefault("calculate.button", "Calculate"));

        // Apply RTL/LTR if needed
        applyTextDirection(locale);
    }

    /** Apply text direction for RTL languages **/
    private void applyTextDirection(Locale locale) {
        boolean isRTL = locale.getLanguage().equals("fa");

        Platform.runLater(() -> {
            if (rootVBox != null) {
                rootVBox.setNodeOrientation(isRTL ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
            }
            String alignment = isRTL ? "-fx-text-alignment: right; -fx-alignment: center-right;"
                    : "-fx-text-alignment: left; -fx-alignment: center-left;";
            txtDistance.setStyle(alignment);
            txtConsumption.setStyle(alignment);
            txtPrice.setStyle(alignment);
        });
    }
}
