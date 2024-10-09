# Release 2 documentation

## Work habits

For this release we started using pair programming where we found it helpful. For small tasks we found it more effective to distribute and solve them individually, but for more advanced tasks we programmed in pairs to find a solution together.

We have included credit to the person we pair programmed with in the commits where it applied.

## Workflow

### Persistence

Some changes in persistence were necessary for release 2.

This application uses implicit storage for persistence. The reason for using this instead of document metaphor is that we want to make the app as user friendly and intuitive as possible. Saving the workouts automatically after clicking the add button makes it easier for the user as they don't need to worry about where to save it or losing the data if forgetting to save.

We also changed the file format from txt to JSON to make the data more organized. Key and value pairs makes it easier for later relases, as we may want to extend the application with features as adding date, duration etc. to the workouts.

### Tasks

For this release the main tasks to solve were the following:

- Make a persistence module
- Change the persistence from writing to a txt file to using JSON
- Write tests for all methods in core module, ui module and persistence module
- Create an architecture diagram
- Update pom files with JaCoCo, Spotbugs and Checkstyle plugins
- Write documentation
- Update readme.md

We distributed the tasks so that everyone had approximately the same amount of work to do, and assigned GitLab issues based on them.

## Code quality

To ensure good code quality we include tests for each module, and try to keep the test coverage as high as possible by testing all the main functionalities of the application.

We try to keep the test coverage as close to 100% as possible, but some of the modules has a lower percent. In the ui module we have lower coverage because App.java isn't tested, but this is because the class has no functionality. The functionality we thought was necessary to test in this module is in the controller class.

We use the following plugins to improve the code quality further:

- **JaCoCo** to make sure that the test coverage is as high as possible and that every method is tested for each class
- **Checkstyle** and **Spotbugs** to help us find bugs that should be fixed for better code quality that adheres to coding standards
