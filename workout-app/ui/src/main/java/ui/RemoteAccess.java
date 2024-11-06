package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;

import core.Workout;
import core.WorkoutLog;
import persistence.WorkoutPersistence;

public class RemoteAccess {
    String endpoint = "http://localhost:8080/";
    String filename = "default.json";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    WorkoutPersistence persistence = new WorkoutPersistence();

   // URI endpointUri = new URI("http://localhost:8080/workout/");
    public WorkoutLog getWorkoutLog(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).build();
        
        try{
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200){
                System.out.println(response.body());
            }
        } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        }
        return null;

    }

    public Workout getWorkout(String workoutInput, LocalDate date){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        if(date == null){
            request = HttpRequest.newBuilder().uri(URI.create(endpoint + "workout?workoutInput="+ workoutInput)).build();
        }else {
            request = HttpRequest.newBuilder().uri(URI.create(endpoint + "workout?workoutInput="+ workoutInput + "&date="+date)).build();
        }
        try{
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200){
                System.out.println(response.body());
            }
        } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        }
        return null;
    }

    public Workout addWorkout(Workout workout){
        String jsonPayload = persistence.writeWorkoutAsJson(workout);
        System.out.println(jsonPayload);
       
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint + "workout?workoutInput="+ workout.getWorkoutInput() + "&date="+workout.getDate()))
        .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
        .POST(BodyPublishers.ofString(jsonPayload))
        .build();
        
        try{
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 201 || response.statusCode() == 200){
                System.out.println("Workout added: " +response.body());
            }else{
                System.out.println("Could not add workout: " +response.body() + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        }
        return null;
    }

    public void removeWorkout(Workout workout){
        String jsonPayload = persistence.writeWorkoutAsJson(workout);
        System.out.println(jsonPayload);
       
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint + "workout?workoutInput="+ workout.getWorkoutInput() + "&date="+workout.getDate()))
        .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
        .DELETE()
        .build();
        
        try{
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200 || response.statusCode() == 204){
                System.out.println("Workout added: " + response.body());
            }else{
                System.out.println("Could not add workout: " +response.body() + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        }
        
    }
}
