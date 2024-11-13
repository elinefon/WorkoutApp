package ui;

import core.Workout;
import core.WorkoutLog;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 * AppController to the remote Api service application.
 * This inherit functions from AppController
 */
public class RemoteAppController extends AppController {

  RemoteAccess access;
  private WorkoutLog workoutLog;

  public RemoteAppController(String ip){
    this.access = new RemoteAccess(ip);
  }

  public RemoteAppController(int port){
    this.access = new RemoteAccess(port);
  }
  
  /**
   * initialize: set the values of access (http requests from server) 
   * and workoutlog.
   */
  public void initialize() {
    super.initialize();
    workoutLog = access.getWorkoutLog();
    
    updateTableView();
  }

  /**
   * checkInvalidWorkoutInput: Checks if workout input is a special character, space is allowed.
   *
   * @param workout
   * @return true if there are special characters, false if not
   */
  public boolean checkInvalidWorkoutInput(Workout workout){
    return workout.getWorkoutInput().matches(".*[^a-zA-Z0-9 ].*");
  }

  /**
   * handleRegister: running when user have written in the description area and clicked 
   * on register. 
   * If the superfunction was running (the input field was not empty -> workout is not null) the
   * if sentence will run adding the workout to the log and persictence.
   * Checks for special characters, will only allow space.
   * After running all the fields will be clear.
   */
  @FXML
  public Workout handleRegister() {
    Workout newWorkout = super.handleRegister();
    if (newWorkout != null) {
      if (checkInvalidWorkoutInput(newWorkout)){
        errorLabel.setText("There can be no special characters");
        return null;
      }
      workoutLog.addWorkout(newWorkout);
      access.addWorkout(newWorkout);

      updateTableView(); //update table
      
      inputWorkout.clear(); //clear input field to allow for a new input
      inputDate.setValue(null);
      errorLabel.setText("");
    }
    return newWorkout;
  }

  /**
   * handleEdit: fired when user is doubleclicking on an element in the workoutList
   * the element is removed from the list and the date and input is added to the input field.
   * Super returns weather this function passed the tests for editing. 
   * This function removes Workout and updates the table.
   *
   * @param w (workout to edit)
   */
  public boolean handleEdit(Workout w) {
    boolean edit = super.handleEdit(w);
    if (edit) {
      workoutLog.removeWorkout(w);
      access.removeWorkout(w);
      updateTableView();
      return true;
    }
    return false;
  }

  /**
   * handleDelete: fired on the delete button after activating an element.
   * Super returns an list of workouts that are selected for removing from workout.
   */
  public ObservableList<Workout> handleDelete() {
    ObservableList<Workout> selectedRows = super.handleDelete();
    for (Workout w : selectedRows) {
      access.removeWorkout(w);
      workoutLog.removeWorkout(w);
    }
    updateTableView();
    return selectedRows;
  }

  
  /**
   * handleClear: fired on clear button
   * Removes all elements.
   */
  @Override
  public void handleClear(){
    for (Workout workout : workoutLog.getWorkouts()){
      access.removeWorkout(workout);
      workoutLog.removeWorkout(workout);
    }
    updateTableView();
  }

  /**
   * updateTableView: updates the table according to the workoutLog
   * Also need to sort all the workouts in order of date.
   */
  @Override
  public void updateTableView() {
    workoutLog.sortByDate();
    workoutsList.getItems().clear(); //clear existing items to prevent doubles
    workoutsList.getItems().addAll(workoutLog.getWorkouts()); //adds items from workoutlog to the table
  }
}
