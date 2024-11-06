# Release 3 documentation

## Work habits

We have continued to use pair programming on more difficult tasks while doing simpler tasks individually. We divided the group into two pairs, with different pairs from release 2.
Even though there are pairs that are the default partner for pair programming and merge request reviews, we have allowed ourselves to be flexible for practical reasons.
Credit is included for pair programmed commitst the same way as in release 2.

## Workflow

### UI

There has been added several new features to the UI to satisfy the requirement for either additional features or a client using a different technology like react.

These features include a delete, clear and edit button as well as a date picker.

### Core

Workouts now have a date to accomidate the new date feature

### Rest/Integrationtests

These new modules are where the client and server communication lies as well as ways to launch a server and client together for proper testing.

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

We try to keep the test coverage as close to 100% as possible, but some of the modules has a lower percent. In the ui module we have lower coverage because App.java isn't tested, but this is because the class has no functionality. The functionality we thought was necessary to test in this module is in the controller class.

Over all every module has 70% or higher code coverage, with the core module having 100%

We use the following plugins to improve the code quality further:

- **JaCoCo** to make sure that the test coverage is as high as possible and that every method is tested for each class
- **Checkstyle** and **potbugs** to help us find bugs that should be fixed for better code quality that adheres to coding standards