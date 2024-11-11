package ui;

import core.Workout;
import core.WorkoutLog;
import javafx.collections.ObservableList;
import persistence.WorkoutPersistence;

/**
 * AppController to the local storage application.
 * This inherits functions from AppController.
 */
public class LocalAppController extends AppController {

  private WorkoutPersistence persistence;
  private String fileName;
  private WorkoutLog workoutLog;
    
  /**
   * Sets the values of workoutLog, filename and persistence.
   */
  public void initialize() {
    super.initialize();

    workoutLog = new WorkoutLog();
    persistence = new WorkoutPersistence(); 
    updateFileName("myWorkout.JSON"); 
  }

  /**
   * handleRegister: running when user have written in the description area and clicked 
   * on register.
   * If the superfunction was running (the input field was not empty -> workout is not null) the
   * if sentence will run adding the workout to the log and persictence.
   * After running all the fields will be clear.
   */
  public Workout handleRegister() {
    Workout newWorkout = super.handleRegister();
    if (newWorkout != null) {
      workoutLog.addWorkout(newWorkout); //adds that new workout to the log
      persistence.saveWorkoutLog(workoutLog, fileName); //save the workout to persistence

      updateTableView(); //update table
            
      inputWorkout.clear(); //clear input field to allow for a new input
      inputDate.setValue(null);
      errorLabel.setText("");
    }
    return newWorkout;
  }

  /**
   * Fired when user is doubleclicking on an element in the workoutList,
   * the element is removed from the list and the date and input is added to the input field.
   * Super returns weather this function passed the tests for editing. 
   * This function removes Workout and updates the table
   *
   * @param w Workout to edit
  */
  public boolean handleEdit(Workout w) {
    boolean edit = super.handleEdit(w);
    if (edit) {
      workoutLog.removeWorkout(w);
      updateTableView();
      return true;
    }
    return false;
  }  

  /**
   * handleDelete: fired on the delete button after activating an element.
   * Super returns an list of workouts that are selected for removing from workout
   */
  public ObservableList<Workout> handleDelete() {
    ObservableList<Workout> selectedRows = super.handleDelete();
    for (Workout w : selectedRows) {
      workoutLog.removeWorkout(w);
    }
    persistence.saveWorkoutLog(workoutLog, fileName);
    updateTableView();
    return selectedRows;
  }

  /**
   * Fired on clear button. Removes all elements.
   */
  public void handleClear() { 
    for (Workout workout : workoutLog.getWorkouts()) {
      workoutLog.removeWorkout(workout);
    }
    persistence.saveWorkoutLog(workoutLog, fileName);
    updateTableView();
  }

  /**
   * This updates the filename and the table so that you 
   * can write to another json file
   * public so that tests can be written in another file.
   */
  public void updateFileName(String fileName) {
    this.fileName = fileName;
    workoutLog = persistence.loadWorkoutLog(fileName);
    updateTableView();
  }

  /**
   * Updates the table according to the workoutLog.
   * Also need to sort all the workouts in order of date
   */
  public void updateTableView() {
    workoutLog.sortByDate();
    //clear existing items to prevent doubles
    workoutsList.getItems().clear();
    //adds items from workoutlog to the table
    workoutsList.getItems().addAll(workoutLog.getWorkouts());
  } 
}
