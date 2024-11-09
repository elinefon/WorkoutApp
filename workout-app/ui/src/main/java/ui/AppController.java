package ui;

import core.Workout;
import core.WorkoutLog;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import persistence.WorkoutPersistence;


/**
 * Controller class for the main page.
 */
public class AppController {

  @FXML
  private TextArea inputWorkout;

  @FXML
  private DatePicker inputDate;

  @FXML
  private TableView<Workout> workoutsList;

  @FXML
  private TableColumn<Workout, String> workoutsColumn;

  @FXML
  private TableColumn<Workout, String> dateColumn;

  @FXML
  private Label errorLabel;

  @FXML
  private Button deleteButton;

  @FXML
  private Button editButton;

  private WorkoutLog workoutLog;
  private WorkoutPersistence persistence;
  private String fileName;

  /**
   * Initializes what the user sees when launching the app.
   */
  @FXML
  public void initialize() {
    workoutLog = new WorkoutLog(); 
    // Makes the user unable to write in the date picker field, to ensure valid date format
    inputDate.setEditable(false);

    workoutsList.setEditable(true);
    // Set up the TableColumn to display the input property
    workoutsColumn.setCellValueFactory(new PropertyValueFactory<>("workoutInput"));
    workoutsColumn.setOnEditStart(e -> {
      handleEdit(); });

    // Set up the TableColumn to display the input property
    workoutsColumn.setCellValueFactory(new PropertyValueFactory<>("workoutInput"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    
    persistence = new WorkoutPersistence();

    updateFileName("myWorkout.JSON"); //Loads previously saved workouts and updates the table
    
    workoutsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    deleteButton.setDisable(true);
    editButton.setDisable(true);

    // Listener to check if user selected row
    workoutsList.getSelectionModel().selectedItemProperty().addListener((obs,
        oldSelection, newSelection) -> {
      //If no row is selected, disable the buttons
      deleteButton.setDisable(newSelection == null);
      editButton.setDisable(newSelection == null);
    });
  }

  /**
   * Handles what happens when the user presses the register button.
   */
  @FXML
  public void handleRegister() {
    String session = inputWorkout.getText();
    LocalDate date = inputDate.getValue();

    errorLabel.setText("");

    if (!session.isEmpty()) {
      if (date == null) {
        date = LocalDate.now();
      } else if (date.isAfter(LocalDate.now())) {
        errorLabel.setText("Date can not be in the future");
        return;
      }
      //Creates new workout from what the user typed into input
      Workout newWorkout = new Workout(session, date);
      
      workoutLog.addWorkout(newWorkout); //Adds that new workout to the log

      persistence.saveWorkoutLog(workoutLog, fileName); //Saves the workout to persistence

      updateTableView(); //Updates table
      
      inputWorkout.clear(); //Clears input field to allow for a new input
      inputDate.setValue(null);
      errorLabel.setText("");
    }
  }
  
  /**
   * Handles what happens when the user double clicks on a row or presses edit button.
   */
  public void handleEdit() { //fired when double clicking element or clicking edit button.
    Workout selectedWorkout = workoutsList.getSelectionModel().getSelectedItem();
    if (inputWorkout.getText().equals("")) {
      inputWorkout.setText(selectedWorkout.getWorkoutInput());
      inputDate.setValue(selectedWorkout.getDate());
      workoutLog.removeWorkout(selectedWorkout);
      updateTableView();
    }
  }

  /**
   * Deletes a workout from the log.
   */
  public void handleDelete() {
    ObservableList<Workout> selectedRows;
    selectedRows = workoutsList.getSelectionModel().getSelectedItems();
    for (Workout w : selectedRows) {
      workoutLog.removeWorkout(w);
    }
    persistence.saveWorkoutLog(workoutLog, fileName);
    updateTableView();
  }

  /**
   * Clears the whole table of workouts.
   */
  @FXML
  public void handleClear() { //Triggers on clicking "clear all" button
    for (Workout workout : workoutLog.getWorkouts()) {
      workoutLog.removeWorkout(workout);
    }
    persistence.saveWorkoutLog(workoutLog, fileName);
    updateTableView();
  }

  /**
   * This updates the filename and the table so that you can write to another json file.
   *
   * @param fileName the filename to update
   */
  public void updateFileName(String fileName) {
    this.fileName = fileName;
    workoutLog = persistence.loadWorkoutLog(fileName);
    updateTableView();
  }

  private void updateTableView() {
    workoutLog.sortByDate();
    //Clears existing items to prevent doubles
    workoutsList.getItems().clear();
    //Adds items from workoutlog to the table
    workoutsList.getItems().addAll(workoutLog.getWorkouts());
  }

}
