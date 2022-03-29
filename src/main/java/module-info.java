module com.example.term3group1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.term3group1 to javafx.fxml;
    exports com.example.term3group1;
}