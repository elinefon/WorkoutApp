
# Workout Log

[open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/gr2405/gr2405/workout-app?new)

A repository with a javafx project, with maven setup for Java 17 and JavaFX 17, and JUnit 5 (Jupiter) and TestFX for testing.

## [workout-app](/workout-app/)

Contains the source code for the workout logging app

The project is a maven modules project with three modules; [core](/workout-app/core/), [persistence](/workout-app/persistence/) and [ui](/workout-app/ui/)

## Features

The completed app will have the following features:

- Log individual workout session with date, duration and type of workout
- Save and load logs automatically upon creation/startup
- Editing previously made logs

## Run project

### Application

Before running the app, run the command **mvn clean install** from [it1901/gr2405/gr2405/workout-app](/workout-app/)

To run the app (App.java, located in it1901/gr2405/gr2405/workout-app/ui/src/main/java/ui), navigate to the [ui](/workout-app/ui/) folder and run the command **mvn javafx:run**

### Tests

To run the tests, navigate to [it1901/gr2405/gr2405/workout-app](/workout-app/) and run the command **mvn test**

How to check the test coverage after running tests:

- Navigate to the module you want to check (ui, persistence or core), then to **target/site/jacoco**
- Run **index.html** in browser
