package ui;

import java.util.List;

import core.Workout;
import core.WorkoutLog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;

public class AppController {

    //adding all fields in app

    @FXML
    private TextArea input_workout;

    @FXML
    private Button register_button;

    @FXML
    private TableView<Workout> workouts_list;

    @FXML
    private TableColumn<Workout, String> workouts_column;

    private WorkoutLog workoutLog;

    @FXML
    public void initialize() {
        workoutLog = new WorkoutLog(); //creates a new workout log instance

        // Set up the TableColumn to display the input property
        workouts_column.setCellValueFactory(new PropertyValueFactory<>("workoutInput"));

        //loading previous workouts and updating the table
      //  workoutLog.loadWorkouts();
        updateTableView();

    }

    @FXML
    public void handleRegister() {
        String session = input_workout.getText();
        if (!session.isEmpty()) {
            Workout newWorkout = new Workout(session); //create new workout from what the user typed into input
      //      workoutLog.saveWorkout(newWorkout); //adds that new workout to the log

            updateTableView(); //see function below

            input_workout.clear(); //clear input field to allow for a new input
        }
    }

    private void updateTableView() {
        workouts_list.getItems().clear(); //clear existing items to prevent doubles
        workouts_list.getItems().addAll(workoutLog.getWorkouts()); //adds items from workoutlog to the table
    }
  
}
