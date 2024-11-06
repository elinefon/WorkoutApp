module workoutApi {
    requires core;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.beans;
    requires spring.context;

    exports springboot.workoutApi to spring.beans, spring.context, spring.web;

    opens springboot.workoutApi to spring.beans, spring.context, spring.web;

}
