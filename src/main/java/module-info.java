module org.example.snakeandleadder {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample to javafx.fxml;
    exports sample;
}