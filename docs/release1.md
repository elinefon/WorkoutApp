# Release 1 documentation

## How we met the requirements

Before we started working on the project, we created a milestone in GitLab called "First release" and added all the issues we planned to solve during the first release in an issue board.

To meet the requirements we created the following issues:

- **Testing issues**: Test that the issues works as expected; how to set labels and assignees, create branch from issue.
- **Set up modules project**: Set up the maven modules project from the template.
- **Update folder and file names**: Change the file and folder names from the template names to the names of our project.
- **JavaFX GUI**: Create a simple UI for the application.
- **Implement Workout and WorkoutLog**: Implement the functionality of the Workout and WorkoutLog java classes. These are dependent of each other and should both be made by the same person.
- **Readme features file**: Write the markdown file with app description and the first user story describing the functionality of the app.
- **Readme**: Write the root markdown file about the project structure and folders.
- **Persistence**: Make a class for writing to file (used for adding the workouts to history).
- **Test writing to file**: Make a JUnit test class for testing writing to file.
- **Documentation**: Write documentation about how we worked with the assignment and distributed the tasks.
- **Controller**: Make a controller class.
- **Bug remove calc.core**: Find out why we can't remove "calc.core" that appears in some of the classes. This does not crash the code, but we can't find out why it is there.
- **fix-devfile**: Bug that appeared when making link to Eclipse Che. Something wrong with the devfile that needed to be fixed.
- **Bug FileNotFound**: Bug that appeared when trying to run the project in Maven. The app run successfully in Java, but Maven can't find the file we want to write to.

## Work distribution

Every issue was assigned to a group member so that the tasks were distributed as evenly as possible.

Bugs that appeared were fixed by looking at it collaborately to try to help each other when stuck, but we originally distributed the issues like this:

- **Testing issues**: Everyone tested this together to make sure that everyone learned how to use issues in the project.
- **Set up modules project**: Eline
- **Update folder and file names**: Ingvild
- **JavaFX GUI**: Eline
- **Implement Workout and WorkoutLog**: Eline
- **Readme features file**: Ida
- **Readme**: Arild
- **Persistence**: Ingvild
- **Test writing to file**: Arild
- **Documentation**: Ida
- **Controller**: Eline
- **Bug remove calc.core**: Bug
- **fix-devfile**: Bug
