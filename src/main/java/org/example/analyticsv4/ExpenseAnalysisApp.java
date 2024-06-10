package org.example.analyticsv4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ExpenseAnalysisApp extends Application {
    private BorderPane root;
    private AnalyticsPane analyticsPane;
    private ComboBox<String> monthSelector;

    private static final String[] MONTHS ={
            "January", "February", "March", "April","May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage){
        primaryStage.setTitle("Expense Analysis");

        root = new BorderPane();
        analyticsPane = new AnalyticsPane();

        monthSelector = new ComboBox<>();
        monthSelector.getItems().addAll(MONTHS);
        monthSelector.setValue(MONTHS[0]);
        monthSelector.setOnAction(e -> {
            int selectedMonthIndex = monthSelector.getSelectionModel().getSelectedIndex() + 1;
            analyticsPane.updateChart(selectedMonthIndex);
        });

        root.setRight(monthSelector);
        root.setCenter(analyticsPane);

        Scene scene = new Scene(root, 1000, 600);
        //String css = this.getClass().getResource("/BarChartCSS.css").toExternalForm();
        //scene.getStylesheets().add(getClass().getResource("/BarChartCSS.css").toExternalForm());
        //scene.getStylesheets().add(css);
        String cssFile = getClass().getResource("/BarChartCSS.css").toExternalForm();
        scene.getStylesheets().add(cssFile);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
