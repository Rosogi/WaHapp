module com.example.weatherandhoroscopeappv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jsoup;


    opens com.example.weatherandhoroscopeappv2 to javafx.fxml;
    exports com.example.weatherandhoroscopeappv2;
}