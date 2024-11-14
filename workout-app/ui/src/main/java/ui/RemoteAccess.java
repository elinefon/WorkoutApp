package ui;

import core.Workout;
import core.WorkoutLog;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import persistence.WorkoutPersistence;

/**
 * This class is implemented in the remote app controller and is used to separate
 * the http requests and responses from the functionality of the application.
 */
public class RemoteAccess {

  String endpoint;
  private static final String APPLICATION_JSON = "application/json";
  private static final String CONTENT_TYPE_HEADER = "Content-Type";

  WorkoutPersistence persistence;

  public RemoteAccess(String ip){
    persistence = new WorkoutPersistence();
    endpoint = "http://" + ip + ":8080";
  }

  public RemoteAccess(int port){
    persistence = new WorkoutPersistence();
    endpoint = "http://localhost:" + port;
  }

  /**
   * convertInputtoURI: makes the input into format that the URI accepts,
   * spaces will be changed to underscore.
   *
   * @param input the string to replace
   * @return a formatted version of the string
   */
  private String convertInputtoURI(String input) {
    return input.replace(" ", "_");
  }

  /**
   * getWorkoutLog: collects all workouts from the api.
   *
   * @return all the workouts in the api
   * @deprecated throws RuntimeException if there is an error getting the http response
   */
  public WorkoutLog getWorkoutLog() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint + "/")).build();

    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        String jsonstr = "{\"workouts\" : " + response.body() + "}";
        System.out.println(jsonstr);
        WorkoutLog wlog = persistence.readValueToWorkoutLog(jsonstr);
        return wlog;
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new WorkoutLog();
  }

  /**
   * getWorkout: get the individual workout from the api, based on the workoutInput.
   *
   * @param workoutInput the input of the workout
   * @param date is not required and might be null
   * @return the workout from the api
   * @throws RuntimeException if there is an error getting the http response
   */
  public Workout getWorkout(String workoutInput, LocalDate date) {
    String workoutInputURI = convertInputtoURI(workoutInput);
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;
    if (date == null) {
      request = HttpRequest.newBuilder().uri(URI.create(endpoint
      + "/workout?workoutInput=" + workoutInputURI)).build();
    } else {
      request = HttpRequest.newBuilder().uri(URI.create(endpoint
      + "/workout?workoutInput=" + workoutInputURI + "&date=" + date)).build();
    }
    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        System.out.println("Recieved object:" + response.body());
        return persistence.readValueToWorkout(response.body());
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  /**
   * addWorkout: adds workout to the api.
   *
   * @param workout the workout to add
   * @return workout if the workout was added to the api, null otherwise
   * @throws RuntimeException if there is an error getting the http response
   */
  public Workout addWorkout(Workout workout) {
    String jsonPayload = persistence.writeWorkoutAsJson(workout);
    System.out.println(jsonPayload);
    
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint + "/workout?workoutInput="
        + convertInputtoURI(workout.getWorkoutInput()) + "&date=" + workout.getDate()))
        .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
        .POST(BodyPublishers.ofString(jsonPayload))
        .build();
    
    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      if (response.statusCode() == 201 || response.statusCode() == 200) {
        System.out.println("Workout added: " + response.body());
        return workout;
      } else {
        System.out.println("Could not add workout: " + response.body() + response.statusCode());
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  /**
   * removeWorkout: removes workout from the api.
   *
   * @param workout the workout to remove
   * @throws RuntimeException if there is an error getting the http response
   */
  public void removeWorkout(Workout workout) {
    
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint + "/workout?workoutInput="
        + convertInputtoURI(workout.getWorkoutInput()) + "&date=" + workout.getDate()))
        .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
        .DELETE()
        .build();
    
    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      if (response.statusCode() == 200 || response.statusCode() == 204) {
        System.out.println("Workoutremoved: " + response.body());
      } else {
        System.out.println("Could not remove workout: " + response.body() + response.statusCode());
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
      
  }
}
