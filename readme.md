
# Workout Log

[open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2024/gr2405/gr2405)

A repository with a javafx project, with maven setup for Java 17 and JavaFX 17, and JUnit 5 (Jupiter) and TestFX for testing.

To see more recommended extentions for running the app, see [extension file](/.vscode/extensions.json/)

## [workout-app](/workout-app/)

Contains the source code for the workout logging app. The user can run the app locally or remote with Springboot.

The project is a maven modules project with four modules:

### [core](/workout-app/core/)

Contains the core logic of the app and related tests.

### [persistence](/workout-app/persistence/)

Handles persistence with json and related tests.

### [ui](/workout-app/ui/)

Contains controller classes and app classes for the user interface of local and remote app, including related tests.

### [workoutApi](/workout-app/workoutApi/)

Contains classes related to the Springboot REST API (rest controller, service class, configuration class and api application class), integration test and related tests.

## Features

The completed app has the following features:

- Log individual workout sessions with date and name of workout
- Save and load logs automatically upon creation/startup
- Editing previously made logs
- Delete singular workouts or the entire log

## Run project

Before running the app, run the command **mvn clean install** from [it1901/gr2405/gr2405/workout-app](/workout-app/)

### Local application

To run the local app (App.java, located in [it1901/gr2405/gr2405/workout-app/ui/src/main/java/ui](/workout-app/ui/src/main/java/ui/)), navigate to the [ui](/workout-app/ui/) module and run the command **mvn javafx:run**. When you get to the welcome window, click "Local App".

### Remote application

To run the remote app you need to have two terminals open (splitted terminals). In one of them, navigate to the [springboot.workoutApi](/workout-app/springboot/workoutApi) module and run the command **mvn spring-boot:run**. After that, navigate to the [ui](/workout-app/ui/) module in the other terminal and run the command **mvn javafx:run**. When you get to the welcome window, click "Remote App".

### Shippable product

(sett inn når det funker)

### Tests

To run the tests, navigate to [it1901/gr2405/gr2405/workout-app](/workout-app/) and run the command **mvn test**

How to check the test coverage after running tests:

- Navigate to the module you want to check (ui, persistence or core), then to **target/site/jacoco**
- Open **index.html** in browser

To run the [integration test](/workout-app/workoutApi/src/test/java/springboot/workoutApi/WorkoutAppIT.java/), navigate to the [workoutApi](/workout-app/workoutApi/) module and run the command **mvn verify**
