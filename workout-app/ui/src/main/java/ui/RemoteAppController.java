package ui;

import core.Workout;
import core.WorkoutLog;
import javafx.collections.ObservableList;
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

    RemoteAccess access;
    

    public void initialize() {
        access = new RemoteAccess();
    }

    public Workout handleRegister() {
        System.out.println(LocalDate.now());
        access.getWorkoutLog();
        access.getWorkout("Arms", LocalDate.now());

        Workout workout = new Workout("YSSSS");

        access.addWorkout(workout);
        access.removeWorkout(workout);
        return null;
    }

    public void handleEditDoubleClick(Workout w) {
    }

    public void handleEditButton() {
    }

    public ObservableList handleDelete() {
        return null;
    }

    public void handleClear(){ //Triggers on clicking "clear all" button
    }

    @Override
    public void updateTableView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTableView'");
    }

    @Override
    public void updateFileName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFileName'");
    }

   
   

    
}
