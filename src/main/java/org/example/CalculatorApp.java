package org.example;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        URL fxmlUrl = getClass().getResource("/org/example/calculator-view.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlUrl);

        VBox root = loader.load();

        // Create scene first
        Scene scene = new Scene(root, 500, 360);

        // Then add CSS
        scene.getStylesheets().add(getClass().getResource("/org/example/style.css").toExternalForm());

        primaryStage.setTitle("Fuel Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
