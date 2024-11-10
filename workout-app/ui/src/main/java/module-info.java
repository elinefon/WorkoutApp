module ui {
    requires core;
    requires persistence;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires java.net.http;

    opens ui to javafx.graphics, javafx.fxml;
}
