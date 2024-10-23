package ui;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.time.Duration;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    private Workout getLatestWorkout() {
        String fxid = "#workouts_list";
        TableView<Workout> workout_list = (TableView<Workout>) getRootNode().lookup(fxid);
        ObservableList<Workout> observable_workout_list = workout_list.getItems();

        if (observable_workout_list.isEmpty()) {
            return null;
        }
        return observable_workout_list.get(observable_workout_list.size() - 1);
    }

    //Checks if there exist a workout with same input and date as workout
    private boolean workoutLogContainsWorkout(Workout workout){
        String fxid = "#workouts_list";
        TableView<Workout> workout_list = (TableView<Workout>) getRootNode().lookup(fxid);
        ObservableList<Workout> observable_workout_list = workout_list.getItems();

        System.out.println(observable_workout_list +", "+ workout);

        if (observable_workout_list.isEmpty()) {
            return false;
        }
        
        for (Workout w: observable_workout_list){
            if(w.getWorkoutInput().equals(workout.getWorkoutInput()) && w.getDate().equals(workout.getDate())){
                return true;
            }
        }
        return false;
    }

    private int getAmountWorkouts() {
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

    private void registerWorkout(String s){
        clickOn("#input_workout" );
        type_string(s);
        pick_date(LocalDate.now());
        clickOn("#register_button");
    }

    private void registerWorkout(String s, LocalDate date){
        clickOn("#input_workout" );
        type_string(s);
        pick_date(date);
        clickOn("#register_button");
    }
/* 
    @ParameterizedTest
    @Order(1)
    @MethodSource("provideWorkoutData")
    public void test_register_button(String input, LocalDate expectedDate){
        registerWorkout(input, expectedDate);
        Workout expectedWorkout = new Workout(input, expectedDate);
        assertTrue(workoutLogContainsWorkout(expectedWorkout));
    }

    private static Stream<Arguments> provideWorkoutData() {
        return Stream.of(
            Arguments.of("cardio", LocalDate.of(2024, 10, 11)),
            Arguments.of("legs", LocalDate.of(2024, 10, 12)),
            Arguments.of("core", LocalDate.of(2024, 10, 13))
        );
    }
*/
    @Test
    @Order(2)
    public void testRegiserButtonWhenEmptyInputField(){
        int workoutLogSize = getAmountWorkouts();
        clickOn("#register_button");
        assertEquals(workoutLogSize, getAmountWorkouts());
    }
 
    @Test
    @Order(3)
    public void testHandleEdit(){
        while (getAmountWorkouts() < 2){
            registerWorkout("swimming");
        }

        Workout original_latest_workout = getLatestWorkout();
        //get the cell to click on
        int workoutLogSize = getAmountWorkouts();
        Node lastCell = lookup("#workouts_list .table-row-cell").nth(workoutLogSize - 1)
                          .lookup(".table-cell:nth-child(1)").query();
        doubleClickOn(lastCell);

        //insert change
        clickOn("#input_workout");
        type_string("change");
        clickOn("#register_button");

        System.out.println(original_latest_workout);

        //check that change was correct
        Workout changed_workout = new Workout(original_latest_workout.getWorkoutInput() + "change", original_latest_workout.getDate());
        assertTrue(workoutLogContainsWorkout(changed_workout));
    }
 
    @Test
    @Order(4)
    public void testHandleEditIfInputFieldNotEmpty(){
        while (getAmountWorkouts() < 1){
            registerWorkout("running");
        }

        clickOn("#input_workout");
        type_string("placeholder");

        int workoutLogSize = getAmountWorkouts();
        Node lastCell = lookup("#workouts_list .table-row-cell").nth(workoutLogSize - 1).lookup(".table-cell:nth-child(1)").query();
        doubleClickOn(lastCell);

        clickOn("#input_workout" );
        type_string("addchange");
        pick_date(LocalDate.now());
        clickOn("#register_button");

        assertTrue(workoutLogContainsWorkout(new Workout("placeholderaddchange")));
    }
}
/*
    @Test
    @Order(5)
    public void testHandleDelete() {
        if (getAmountWorkouts() == 0) { //added sample workout for when the log is empty
            registerWorkout("editthis");
        }

        int originalSize = getAmountWorkouts();
        Node lastRow = lookup("#workouts_list .table-row-cell").nth(originalSize - 1).query();
        clickOn(lastRow);
        clickOn("#deleteButton");
        assertEquals(originalSize - 1, getAmountWorkouts()); //verifies that size has decreased by 1
    }

    @Test
    @Order(6)
    public void testHandleDeleteIfNoWorkoutSelected() {
        if (getAmountWorkouts() == 0) { //added sample workout for when the log is empty
            registerWorkout("editthis");
        }
        int originalSize = getAmountWorkouts();
        clickOn("#deleteButton");
        assertEquals(originalSize, getAmountWorkouts());
    }

    @Test
    @Order(7)
    public void testHandleClear() {
        if (getAmountWorkouts() == 0) { //added sample workout for when the log is empty
            registerWorkout("editthis");
            registerWorkout("another workout");
        }
        clickOn("#clearAllButton");
        assertEquals(0, getAmountWorkouts());
    }
}
*/