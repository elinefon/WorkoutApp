module persistence {
    requires core;
    exports persistence;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;  // Resolve jackson-databind as an automatic module
    requires com.fasterxml.jackson.annotation;

}
