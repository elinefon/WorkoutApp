# Release 3 documentation

## Work habits

We have continued to use pair programming on more difficult tasks while doing simpler tasks individually. We divided the group into two pairs, with different pairs from release 2. Even though there are pairs that are the default partner for pair programming and merge request reviews, we have allowed ourselves to be flexible on review partners for practical reasons.

Credit is included for pair programmed commits the same way as in release 2.

## Workflow

In this release we had the choice between adding new features or creating a new client, here we chose to add more features, due to the fact that our application from release 2 was quite simple, and we wanted the challenge of expanding it.

### UI

There has been added several new features to the UI to satisfy the requirement for either additional features or a client using a different technology like react.

These features include:

- Delete button: there is now possible to delete individual or multiple selected workouts.
- Clear button: will clear the whole workout log for workouts.
- Edit button: there is also possible to edit any previous added workouts. This can either be done by selecting a workout and clicking the edit button or doubleclicking on a workout.
- Date picker: in addition to adding input for each workout there is now a date option to connect the workout to a date. A workout cannot be dated in the future and if there is no date inserted the date will be set to today. The workout log is sorted in order by date.
- Front page for choosing remote or local application. 

In addition to this we have added a remote version of this application that uses the new module workoutApi. All the client HTTP requests are located in UI in the RemoteAccess class that is a support class for the remote controller.

### Core

Workouts now have a date variable to accomidate the new date feature, and WorkoutLog have a sorting function to sort workouts in order by date.

### Persistence

In persistence we have added methods for reading json value to workout/workout log and from workoutLog to json. This removes the need to import both persistence and objectmapper in other modules.

### WorkoutApi

This new module are where server information is located. In the controller there are method for get/post/delete requests. This contains an instance of the workoutLogService that contains logic that connects the api to the core module.

We debated on having a put method that is used by the edit functionality, however due to the logic of this functionality (we delete the input, add the information in the input fields and add them back again) we decided that using delete and post again would make more sense.

We also debated splitting this module into multiple modules, but we found that since our project is quite small with few files, we decided against this, keeping everything in a single module.

### Tasks

Architecture:

- Build a REST api around the core logic and create a web-server for it
- Change user interface to use the REST api

Functionality:

- Expand the application with new functionality (or have same functionality with a different client technology)
- Application should make use of existing REST api
- Maven shall be used to build the system
- Write tests
- Continue to check code with for example CheckStyle and Spotbugs

Workhabbits:

- Use development tasks actively, they shall be the basis for all work.
- Code shall be connected to development tasks by for example commit messages
- Commit messages shall be descriptive
- You shall make use of merge requests where you can connect your code to issues
- Use code review to improve code quality within the group

Documentation:

- A package diagram for the solition
- A class diagram for the most important parts of the system
- A sequence diagram for an important usecase which showcases how the system functions, including REST-calls
- Documentation of the REST service, which requests are supported
- Documentation for each release placed in seperate folder
- Document each member's contribution in contribution.md
- Include reflection on sustainability in sustainability.md
- Document 2 biggest challenges in challenges.md

## Code quality

To ensure good code quality we continue to include tests for each module, and try to keep the test coverage as high as possible by testing all the main functionalities of the application.

We try to keep the test coverage as high as possible, but at the same time there are some parts that we considered not important to test, this resulting in some of the modules having a lower percent:

### Modules

#### Testing Core

Core is such an important module of our project that all the other module depend on. We therefore aim at 100% test coverage in this module.

#### Testing Persistence

In this module there are a lot of thrown exceptions and although we could test all of these our main priority is to test correct function of the application. If we had more time, we might test all exceptions, but for now we will test the most common case and most common issues. Therefore the jacoco coverage might be lower, but never under 50%, preferably also over 60%.

#### Testing Ui

In the ui module we have lower coverage because App.java isn't tested, but this is because the class in itself has no functionality. It is also indirectly tested when the controller is tested: if all the controller tests crash we then can assume that there is something wrong with launcing the application. Therefore the functionality we thought was necessary to test in this module is in the controller class.

In addition to this we have two controllers one remote and one local controller, they both inherit functions from the abstract app controller class. Even though the saving states are different, there are so much common logic that we decided to test mostly the local application. The methods for delete, clear and updateWorkout log are methods that are similar for both controllers, but only tested in localController test due to the functions being almost the same in the remote and local controller.

The remote controller however have tests for illegal characters and checking if the erroralert is displayed when springboot is not running.

Ui is however an important module were there can be many issues so we aim for over 50% here as well. We have the policy of testing the important parts and focusing less on the coverage number.

#### Testing WorkoutApi

The integration test is a part of this module, this tests, this is not a part of junit test and will not be displayed in the jacoco coverage parts, however it still test the functionality of this module.

We are in the process of writing the last test for the controller, but there are some issues with spring boot application. The api however works as it is supposed to.

### General

We use the following plugins to improve the code quality further:

- **JaCoCo** to make sure that the test coverage is as high as possible and that every method is tested for each class
- **Checkstyle** and **spotbugs** to help us find bugs that should be fixed for better code quality that adheres to coding standards