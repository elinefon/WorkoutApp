package ui;

import core.Workout;
import core.WorkoutLog;
import javafx.collections.ObservableList;

/**
 * AppController to the remote Api service application.
 * This inherit functions from AppController
 */
public class RemoteAppController extends AppController{

    RemoteAccess access;
    private WorkoutLog workoutLog;
    
    public void initialize() {
        super.initialize();
        access = new RemoteAccess();
        workoutLog = access.getWorkoutLog();
       
        updateTableView();
    }

    public Workout handleRegister() {
        Workout newWorkout = super.handleRegister();
        if(newWorkout != null){
            workoutLog.addWorkout(newWorkout);
            access.addWorkout(newWorkout);

            updateTableView(); //update table
            
            inputWorkout.clear(); //clear input field to allow for a new input
            inputDate.setValue(null);
            errorLabel.setText("");

        }return newWorkout;
    }

    public boolean handleEdit(Workout w) {
        boolean edit = super.handleEdit(w);
        if(edit){
            workoutLog.removeWorkout(w);
            access.removeWorkout(w);
            updateTableView();
            return true;
        }return false;
    }

    public ObservableList<Workout> handleDelete() {
        ObservableList<Workout> selectedRows = super.handleDelete();
        for (Workout w : selectedRows) {
            access.removeWorkout(w);
            workoutLog.removeWorkout(w);
        }
        updateTableView();
        return selectedRows;
    }

    public void handleClear(){ //Triggers on clicking "clear all" button
        for (Workout workout : workoutLog.getWorkouts()){
            access.removeWorkout(workout);
            workoutLog.removeWorkout(workout);
        }
        updateTableView();
    }

    @Override
    public void updateTableView() {
        workoutLog.sortByDate();
        workoutsList.getItems().clear(); //clear existing items to prevent doubles
        workoutsList.getItems().addAll(workoutLog.getWorkouts()); //adds items from workoutlog to the table
    }

    @Override
    public void updateFileName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFileName'");
    }

   
   

    
}
