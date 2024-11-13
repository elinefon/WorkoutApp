# Sources

## AI tools for the third release

For release 2 we used ChatGPT for a lot of troubleshooting. At places where we have used AI tools for generating code snippets that we used directly in the project there are comments. 

### List of where ai (Chat GPT) is used:

- Core, Sort by date in [Workoutlog](/workout-app/core/src/main/java/core/WorkoutLog.java)
- Ui, Troubleshooting use of wiremock in [RemoteAppControllerTest](/workout-app/ui/src/test/java/ui/RemoteAppControllerTest.java)
- Ui, CheckValidWorkoutInput method in [RemoteAppController](/workout-app/ui/src/main/java/ui/RemoteAppController.java)
- WorkoutApi, Troubleshooting use of mockMvc/springBootTest in [WorkoutApiApplicationTest](/workout-app/workoutApi/src/test/java/springboot/workoutApi/WorkoutApiApplicationTest.java)

## Other Sources

For creating the Api using springboot we used various sources listed below.

- Used the [spring boot initializer](https://start.spring.io/) to create a spring boot project.
- Used this [tutorial](https://www.youtube.com/watch?v=Zo9xQzibp4Y&t=130s) for the setup of spring boot, with the initializer above.
- Used this [tutorial](https://www.youtube.com/watch?v=Aasp0mWT3Ac) for understanding how mockMvc works in order to write the  [WorkoutApiApplicationTest](/workout-app/workoutApi/src/test/java/springboot/workoutApi/WorkoutApiApplicationTest.java)
- This is the [tutorial](https://www.baeldung.com/introduction-to-wiremock) for the setup of wiremock used in [RemoteAppControllerTest](/workout-app/ui/src/test/java/ui/RemoteAppControllerTest.java)
- Used wiremock-standalone as dependency according to [this documentation](https://wiremock.org/docs/standalone/java-jar/) when encountering issues with jetty dependencies
