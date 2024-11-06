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

    @FXML
    public void initialize() {
        workoutLog = new WorkoutLog(); //creates a new workout log instance

        inputDate.setEditable(false); // Makes the user unable to write in the date picker field

        workoutsList.setEditable(true);
        workoutsColumn.setCellValueFactory(new PropertyValueFactory<>("workoutInput")); // Set up the TableColumn to display the input property
        workoutsColumn.setOnEditStart(e -> {
                handleEdit();});

        workoutsColumn.setCellValueFactory(new PropertyValueFactory<>("workoutInput")); // Set up the TableColumn to display the input property
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        persistence = new WorkoutPersistence(); //Create an persistence object

        updateFileName("myWorkout.JSON"); //loading previous workouts and updating the table
        
        workoutsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        deleteButton.setDisable(true);
        editButton.setDisable(true);

        workoutsList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> { //listener to check if user selected row
            //if no row is selected, disable the buttons
            deleteButton.setDisable(newSelection == null);
            editButton.setDisable(newSelection == null);
        });
    }

    @FXML
    public void handleRegister() {
        String session = inputWorkout.getText();
        LocalDate date = inputDate.getValue();

        errorLabel.setText("");

        if (!session.isEmpty() ) {
            //La denne stå
            if (date == null){
                date = LocalDate.now();
            }
            else if (date.isAfter(LocalDate.now())) {
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
    
    public void handleEdit() { //fired when double clicking element or clicking edit button
        Workout selectedWorkout = workoutsList.getSelectionModel().getSelectedItem();
        if (inputWorkout.getText().equals("")) {
            inputWorkout.setText(selectedWorkout.getWorkoutInput());
            inputDate.setValue(selectedWorkout.getDate());
            workoutLog.removeWorkout(selectedWorkout);
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
