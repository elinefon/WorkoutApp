package ui;

import core.Workout;
import core.WorkoutLog;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import persistence.WorkoutPersistence;
import java.time.LocalDate;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;


public class AppController {

    //adding all fields in app

    @FXML
    private TextArea input_workout;

    @FXML
    private DatePicker input_date;

    @FXML
    private Button register_button;

    @FXML
    private TableView<Workout> workouts_list;

    @FXML
    private TableColumn<Workout, String> workouts_column;

    @FXML
    private TableColumn<Workout, String> date_column;

    @FXML
    private Label error_label;

    private WorkoutLog workoutLog;
    private WorkoutPersistence persistence;
    private String fileName;

    @FXML
    public void initialize() {
        workoutLog = new WorkoutLog(); //creates a new workout log instance

        input_date.setEditable(false); // Makes the user unable to write in the date picker field

        workouts_list.setEditable(true);
        workouts_column.setCellValueFactory(new PropertyValueFactory<>("workoutInput")); // Set up the TableColumn to display the input property
        workouts_column.setOnEditStart(e -> {
                handleEdit(e.getRowValue());});

        workouts_column.setCellValueFactory(new PropertyValueFactory<>("workoutInput")); // Set up the TableColumn to display the input property
        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        persistence = new WorkoutPersistence(); //Create an persistence object

        
        updateFileName("myWorkout.JSON"); //loading previous workouts and updating the table
        
        workouts_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void handleRegister() {
        String session = input_workout.getText();
        LocalDate date = input_date.getValue();

        error_label.setText("");

        if (!session.isEmpty()) {
            if(date == null){
                date = LocalDate.now();
            } else if (date.isAfter(LocalDate.now())) {
                error_label.setText("Date can not be in the future");
                return;
            }
            
            Workout newWorkout = new Workout(session, date); //create new workout from what the user typed into input
            
            workoutLog.addWorkout(newWorkout); //adds that new workout to the log

            persistence.saveWorkoutLog(workoutLog, fileName); //save the workout to persistence

            updateTableView(); //update table
            
            input_workout.clear(); //clear input field to allow for a new input
            input_date.setValue(null);
            error_label.setText("");
        }
    }

    public void handleEdit(Workout w) { //fired when double clicking on element in the workout_list
    
        if(input_workout.getText().equals("")){ //if there is written something in the field this need to be added first

            input_workout.setText(w.getWorkoutInput()); //set the input field
            
            input_date.setValue(w.getDate());

            workoutLog.removeWorkout(w);
            updateTableView();
        }
        
    }

    public void handleDelete() {
        ObservableList<Workout> selectedRows;
        selectedRows = workouts_list.getSelectionModel().getSelectedItems();
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
        workouts_list.getItems().clear(); //clear existing items to prevent doubles
        workouts_list.getItems().addAll(workoutLog.getWorkouts()); //adds items from workoutlog to the table
    }
  
}
