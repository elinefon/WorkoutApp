package ui;

import java.time.LocalDate;

import core.Workout;
import core.WorkoutLog;
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


public class AppController {

    //adding all fields in app

    @FXML
    private TextArea inputWorkout;

    @FXML
    private DatePicker inputDate;

    @FXML
    private Button registerButton;

    @FXML
    private TableView<Workout> workoutsList;

    @FXML
    private TableColumn<Workout, String> workoutsColumn;

    @FXML
    private TableColumn<Workout, String> dateColumn;

    @FXML
    private Label errorLabel;

    private WorkoutLog workoutLog;
    private WorkoutPersistence persistence;
    private String fileName;

    @FXML
    public void initialize() {
        workoutLog = new WorkoutLog(); //creates a new workout log instance

        inputDate.setEditable(false); // Makes the user unable to write in the date picker field

        workoutsList.setEditable(true);
        workoutsColumn.setCellValueFactory(new PropertyValueFactory<>("workoutInput")); // Set up the TableColumn to display the input property
        workoutsColumn.setOnEditStart(e -> {
                handleEdit(e.getRowValue());});

        workoutsColumn.setCellValueFactory(new PropertyValueFactory<>("workoutInput")); // Set up the TableColumn to display the input property
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        persistence = new WorkoutPersistence(); //Create an persistence object

        
        updateFileName("myWorkout.JSON"); //loading previous workouts and updating the table
        
        workoutsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void handleRegister() {
        String session = inputWorkout.getText();
        LocalDate date = inputDate.getValue();

        errorLabel.setText("");

        if (!session.isEmpty() && date != null) {
            if (date.isAfter(LocalDate.now())) {
                errorLabel.setText("Date can not be in the future");
                return;
            }
            
            Workout newWorkout = new Workout(session, date); //create new workout from what the user typed into input
            
            workoutLog.addWorkout(newWorkout); //adds that new workout to the log

            persistence.saveWorkoutLog(workoutLog, fileName); //save the workout to persistence

            updateTableView(); //update table
            
            inputWorkout.clear(); //clear input field to allow for a new input
            inputDate.setValue(null);
            errorLabel.setText("");
        }
    }

    public void handleEdit(Workout w) { //fired when double clicking on element in the workoutList
    
        if(inputWorkout.getText().equals("")){ //if there is written something in the field this need to be added first

            inputWorkout.setText(w.getWorkoutInput()); //set the input field
            
            //TODO: after adding date need to set the date as well

            workoutLog.removeWorkout(w);
            updateTableView();
        }
        
    }

    public void handleDelete() {
        ObservableList<Workout> selectedRows;
        selectedRows = workoutsList.getSelectionModel().getSelectedItems();
        for (Workout w : selectedRows) {
            workoutLog.removeWorkout(w);
        }
        persistence.saveWorkoutLog(workoutLog, fileName);
        updateTableView();
    }

    @FXML
    public void handleClear(){ //Triggers on clicking "clear all" button
        for (Workout workout : workoutLog.getWorkouts()){
            workoutLog.removeWorkout(workout);
        }
        persistence.saveWorkoutLog(workoutLog, fileName);
        updateTableView();
    }

    //public so that tests can be written in another file
    //This updates the filename and the table so that you can write to another json file
    public void updateFileName(String fileName){
        this.fileName = fileName;
        workoutLog = persistence.loadWorkoutLog(fileName);
        updateTableView();
    }

    private void updateTableView() {
        workoutLog.sortByDate();
        workoutsList.getItems().clear(); //clear existing items to prevent doubles
        workoutsList.getItems().addAll(workoutLog.getWorkouts()); //adds items from workoutlog to the table
    }
  
}
