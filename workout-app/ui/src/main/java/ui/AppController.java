package ui;

import core.Workout;
import core.WorkoutLog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import persistence.WorkoutPersistence;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;


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
    private TableColumn<Workout, LocalDate> date_column;

    private WorkoutLog workoutLog;
    private WorkoutPersistence persistence;
    private String fileName;

    @FXML
    public void initialize() {
        workoutLog = new WorkoutLog(); //creates a new workout log instance

        workouts_column.setCellValueFactory(new PropertyValueFactory<>("workoutInput")); // Set up the TableColumn to display the input property

        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        persistence = new WorkoutPersistence(); //Create an persistence object
        
        updateFileName("myWorkout.JSON"); //loading previous workouts and updating the table
    }

    @FXML
    public void handleRegister() {
        String session = input_workout.getText();
        LocalDate date = input_date.getValue();
        if (!session.isEmpty() && date != null) {
            Workout newWorkout = new Workout(session, date); //create new workout from what the user typed into input
            
            workoutLog.addWorkout(newWorkout); //adds that new workout to the log

            persistence.saveWorkoutLog(workoutLog, fileName); //save the workout to persistence

            updateTableView(); //update table
            
            input_workout.clear(); //clear input field to allow for a new input
            input_date.setValue(null);
        }
    }

    //public so that tests can be written in another file
    //This updates the filename and the table so that you can write to another json file
    public void updateFileName(String fileName){
        this.fileName = fileName;
        workoutLog = persistence.loadWorkoutLog(fileName);
        updateTableView();
    }

    private void updateTableView() {
        workouts_list.getItems().clear(); //clear existing items to prevent doubles
        workouts_list.getItems().addAll(workoutLog.getWorkouts()); //adds items from workoutlog to the table
    }
  
}
