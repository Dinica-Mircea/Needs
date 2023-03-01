module com.example.examendubios {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.examendubios.domain to javafx.fxml;
    exports com.example.examendubios.domain;

    opens com.example.examendubios to javafx.fxml;
    exports com.example.examendubios;
}