# Work distribution

For this release, everyone has contributed to adding additional features to the app and testing this. We also wanted everyone to get a grasp of what an API consists of, and what the purpose of it is. Therefore, at the beginning of release 3, we decided to have everyone learn about it. As time went on, we found it would be inefficient to have everyone work on API, so we delegated different tasks to everyone. Two of us continued working on the API and the other two worked on more features, documentation and making the application shippable.

## Arild

* **Features and application:** The feature Arild added was a "clear all" button debugging issues with Eline. He also played a main part in making this application shippable, mostly trouble shooting with saving/loading and connecting a server hosted on a different machine.
* **Documentation:** For documentation, he created the package diagram, class diagram and sequential diagram, and wrote about our biggest challenges in the project and parts of the release 3 documentation.
* **Tests:** He implemented tests in the persistence module, increasing Jacoco coverage to 75%.

## Eline

* **Features and application:** The feature Eline added a was the "delete" button debugging issues with Arild. She added a welcome window that the user is greeted with when launching the app, and she added an edit button to make the edit function more visible to the user, using similar logic to Ingvild's edit by double clicking. Eline also played a main part in making this application shippable. This includes adding an IP input field on the welcome window. 
* **Documentation:** For documentation, she wrote the discussion of sustainability in our app.
* **Tests:** Eline implemented the tests for clearing and deleting workout(s). She also had responsibility for the 100% Jacoco coverage of core.

## Ingvild

* **Features and application:** Main focus this release has been API. For additional features, she added functionality to edit a workout by double clicking it. She also updated the UI module with the new rest controller, moving the previous controller into localController, both extending the abstract class appController. She extended the welcome controller class made by Eline to give the user choice between using the app locally or remotely. Ingvild wrote the remoteAccess class that handles HTTP requests. She also extended the persistence class with methods for objectmapper.
* **Documentation:** She contributed to documentation by writing the user stories, parts of release 3 documentation, the rest documentation and the source documentation.
* **Tests:** Ingvild wrote tests for the editing method she implemented. In the UI module she wrote the tests for the remote controller, remoteAccess and welcome controller. In the workoutApi she pairprogrammed the the tests for the controller and server with Ida.

## Ida

* **Features and application:** The main focus for this release has been to implement the rest API. She pair programmed the rest api module with Ingvild. For additional features, she added the functionality to add dates to the workouts and showing it in the GUI, along with sorting the workout log by date
* **Documentation:** Ida have written close to all documentation for earlier releases and were therefor set on more coding tasks for release 3.
* **Tests:** Ida wrote the integration test for the workoutApi. She had the main responsibility for the tests in the rest api module, and contributed a lot with writing the tests for the controller and server. She also added testing needed for the date functionality in the core module.
