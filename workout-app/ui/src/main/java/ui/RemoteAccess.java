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

  String endpoint = "http://localhost:8080/";
  private static final String APPLICATION_JSON = "application/json";
  private static final String CONTENT_TYPE_HEADER = "Content-Type";

  WorkoutPersistence persistence;

  public RemoteAccess() {
    persistence = new WorkoutPersistence();
  }

  /**
   * Makes the input into format that the URI accepts,
   * spaces will be changed to underscore.
   *
   * @param input the original string to be formatted
   * @return a formatted version of the string
   */
  private String convertInputtoUri(String input) {
    return input.replace(" ", "_");
  }

  /**
   * Collects all workouts from the api.
   *
   * @return all the workouts in the api
   * @throws RuntimeException if there is an error getting the http response
   */
  public WorkoutLog getWorkoutLog() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).build();
    
    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        String jsonstr = "{\"workouts\" : " + response.body() + "}";
        WorkoutLog wlog = persistence.readValueToWorkoutLog(jsonstr);
        return wlog;
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new WorkoutLog();
  }

  /**
   * Gets the individual workout from the api, based on the workoutInput.
   *
   * @param workoutInput the input corresponding to the workout
   * @param date is not required and might be null
   * @return the workout from the api
   * @throws RuntimeException if there is an error getting the http response
   */
  public Workout getWorkout(String workoutInput, LocalDate date) {
    String workoutInputUri = convertInputtoUri(workoutInput);
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;
    if (date == null) {
      request = HttpRequest.newBuilder().uri(URI.create(endpoint + "workout?workoutInput="
        + workoutInputUri)).build();
    } else {
      request = HttpRequest.newBuilder().uri(URI.create(endpoint + "workout?workoutInput=" 
        + workoutInputUri + "&date=" + date)).build();
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
   * Adds workout to the api.
   *
   * @param workout the workout to be added
   * @return Workout if the workout was added to the api, null otherwise
   * @throws RuntimeException if there is an error getting the http response
   */
  public Workout addWorkout(Workout workout) {
    String jsonPayload = persistence.writeWorkoutAsJson(workout);
    System.out.println(jsonPayload);
    
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint + "workout?workoutInput="
        + convertInputtoUri(workout.getWorkoutInput()) + "&date=" + workout.getDate()))
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
   * @param workout the workout to be removed
   * @throws RuntimeException if there is an error getting the http response
   */
  public void removeWorkout(Workout workout) {
    String jsonPayload = persistence.writeWorkoutAsJson(workout);
    System.out.println(jsonPayload);
    
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint + "workout?workoutInput="
        + convertInputtoUri(workout.getWorkoutInput()) + "&date=" + workout.getDate()))
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
