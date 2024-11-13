module workoutApi {
    requires core;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.beans;
    requires spring.context;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;


    exports springboot.workoutApi to spring.beans, spring.context, spring.web;

    opens springboot.workoutApi to spring.beans, spring.context, spring.web;

}
