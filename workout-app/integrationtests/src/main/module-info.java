module integrationtests {
    requires core;
    requires persistence;
    requires ui;
    requires workoutApi;
    
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens integrationtests to javafx.graphics, javafx.fxml;
}
