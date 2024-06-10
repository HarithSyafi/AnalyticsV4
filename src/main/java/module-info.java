module org.example.analyticsv4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.analyticsv4 to javafx.fxml;
    exports org.example.analyticsv4;
}