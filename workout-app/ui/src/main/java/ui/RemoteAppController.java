package ui;

import core.Workout;
import core.WorkoutLog;
import javafx.fxml.FXML;
import persistence.WorkoutPersistence;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;

import java.net.http.HttpResponse;

public class RemoteAppController extends AppController{
    
    String endpoint = "http://localhost:8080/";
    String filename = "default.json";

    public void initialize() {

    }

    public void handleRegister() {
        System.out.println(LocalDate.now());
        getWorkoutLog();
        getWorkout("Arms", LocalDate.now());

        Workout workout = new Workout("YSSSS");

        addWorkout(workout);
    }

    public void handleEditDoubleClick(Workout w) {
    }

    public void handleEditButton() {
    }

    public void handleDelete() {
    }

    public void handleClear(){ //Triggers on clicking "clear all" button
    }

   
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    WorkoutPersistence persistence = new WorkoutPersistence();

   // URI endpointUri = new URI("http://localhost:8080/workout/");
    private WorkoutLog getWorkoutLog(){
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

    private Workout getWorkout(String workoutInput, LocalDate date){
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

    private Workout addWorkout(Workout workout){
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
            if (response.statusCode() == 201){
                System.out.println("Workout added: " +response.body());
            }else{
                System.out.println("Could not add workout: " +response.body());
            }
        } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        }
        return null;
    }

    private void removeWorkout(Workout workout){

    }

    
}
