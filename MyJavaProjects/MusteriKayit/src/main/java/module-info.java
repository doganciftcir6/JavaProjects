module com.example.vizesuleymancinar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vizesuleymancinar to javafx.fxml;
    exports com.example.vizesuleymancinar;
}