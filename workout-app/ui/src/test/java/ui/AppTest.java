package ui;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import core.Workout;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import core.Workout;

/**
 * TestFX App test
 */
public class AppTest extends ApplicationTest {

    private AppController controller;
    private Parent root;
    private FxRobot robot;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("App.fxml"));
        root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.updateFileName("testFile.JSON");
        stage.setScene(new Scene(root));
        stage.show();

        robot = new FxRobot();
    }

    public Parent getRootNode() {
        return root;
    }

    private Workout get_latest_workout() {
        String fxid = "#workouts_list";
        TableView<Workout> workout_list = (TableView<Workout>) getRootNode().lookup(fxid);
        ObservableList<Workout> observable_workout_list = workout_list.getItems();

        if (observable_workout_list.isEmpty()) {
            return null;
        }
        return observable_workout_list.get(observable_workout_list.size() - 1);


    }

    private int getAmoutWorkouts() {
        TableView<Workout> workout_list = (TableView<Workout>) getRootNode().lookup("#workouts_list");
        ObservableList<Workout> obserbable_workout_list = workout_list.getItems();
        return obserbable_workout_list.size();
    }

    private int getAmoutWorkouts() {
        TableView<Workout> workout_list = (TableView<Workout>) getRootNode().lookup("#workouts_list");
        ObservableList<Workout> obserbable_workout_list = workout_list.getItems();
        return obserbable_workout_list.size();
    }


    private void type_string(String string){ 
        String keys[] = string.split("");
        for (String i : keys){
            KeyCode key = KeyCode.getKeyCode(i.toUpperCase());
            robot.press(key);
            robot.release(key);
        }
    }

    private void pick_date(LocalDate date) {
        interact(() -> {
            DatePicker datePicker = (DatePicker) getRootNode().lookup("#input_date");
            datePicker.setValue(date);
        });
    }

    @ParameterizedTest
    @MethodSource("provideWorkoutData")
    public void test_register_button(String input, LocalDate expectedDate){
        Workout original_latest_workout = get_latest_workout();
        clickOn("#input_workout" );
        type_string(input);
        pick_date(expectedDate);
        clickOn("#register_button");
        Workout new_latest_workout = get_latest_workout();
        Workout expectedWorkout = new Workout(input, expectedDate);
        assertNotEquals(original_latest_workout, new_latest_workout);
        assertEquals(new_latest_workout.toString(), expectedWorkout.toString());
    }

    private static Stream<Arguments> provideWorkoutData() {
        return Stream.of(
            Arguments.of("cardio", LocalDate.of(2024, 10, 11)),
            Arguments.of("legs", LocalDate.of(2024, 10, 12)),
            Arguments.of("core", LocalDate.of(2024, 10, 13))
        );
    }

    @Test
    public void testRegiserButtonWhenEmptyInputField(){
        int workoutLogSize = getAmoutWorkouts();
        clickOn("#register_button");
        assertEquals(workoutLogSize, getAmoutWorkouts());
    }
    
    @Test
    public void testHandleEdit(){
        if (getAmoutWorkouts() == 0) { //added sample workout for when the log is empty
            clickOn("#input_workout");
            type_string("sampleWorkout");
            clickOn("#register_button");
        }

        Workout original_latest_workout = get_latest_workout();
        //get the row to click on
        int workoutLogSize = getAmoutWorkouts();
        Node lastRow = lookup("#workouts_list .table-row-cell").nth(workoutLogSize-1).query();
        doubleClickOn(lastRow);
        //insert change
        clickOn("#input_workout" );
        type_string("change");
        clickOn("#register_button");

        //check that change was correct
        Workout new_latest_workout = get_latest_workout();
        assertNotEquals(original_latest_workout, new_latest_workout);
        assertEquals(new_latest_workout.toString(), (new Workout(original_latest_workout.getWorkoutInput() + "change")).toString());

    }

    @Test
    public void testHandleEditIfInputFieldNotEmpty(){
        clickOn("#input_workout");
        type_string("placeholder");

        int workoutLogSize = getAmoutWorkouts();
        Node lastRow = lookup("#workouts_list .table-row-cell").nth(workoutLogSize-1).query();
        doubleClickOn(lastRow);
        clickOn("#input_workout" );
        type_string("addchange");
        clickOn("#register_button");

        assertEquals(get_latest_workout().toString(), (new Workout("placeholderaddchange").toString()));
    }

    @Test
    public void testHandleDelete() {
        int originalSize = getAmoutWorkouts();
        Node lastRow = lookup("#workouts_list .table-row-cell").nth(originalSize - 1).query();
        clickOn(lastRow);
        
        clickOn("#deleteButton");
        
        assertEquals(originalSize - 1, getAmoutWorkouts()); //verifies that size has decreased by 1
    }

    @Test
    public void testHandleDeleteIfNoWorkoutSelected() {
        int originalSize = getAmoutWorkouts();
        clickOn("#deleteButton");
        assertEquals(originalSize, getAmoutWorkouts());
    }

    @Test
    public void testHandleClear() {
        clickOn("#clearAllButton");
        assertEquals(0, getAmoutWorkouts());
    }
}
