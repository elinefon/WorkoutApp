package ui;

import core.Workout;
import javafx.fxml.FXML;

public class RemoteAppController {
    
 
    String endpointUri = "http://localhost:8080/workout/";

    public void initialize() {

    }

    public void handleRegister() {
        System.out.println(endpointUri);
    }

    public void handleEditDoubleClick(Workout w) {
    }

    public void handleEditButton() {
    }

    public void handleDelete() {
    }

    public void handleClear(){ //Triggers on clicking "clear all" button
    }

    
}
