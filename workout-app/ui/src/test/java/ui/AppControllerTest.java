package ui;
import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import core.Workout;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * TestFX App testrunning
 */
public class AppControllerTest extends ApplicationTest {

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
        String fxid = "#workoutsList";
        TableView<Workout> workoutList = (TableView<Workout>) getRootNode().lookup(fxid);
        ObservableList<Workout> observableWorkoutList = workoutList.getItems();

        if (observableWorkoutList.isEmpty()) {
            return null;
        }
        return observableWorkoutList.get(observableWorkoutList.size() - 1);
    }

    private boolean workoutLogContainsWorkout(Workout workout){
        String fxid = "#workoutsList";
        TableView<Workout> workoutList = (TableView<Workout>) getRootNode().lookup(fxid);
        ObservableList<Workout> observableWorkoutList = workoutList.getItems();

        if (observableWorkoutList.isEmpty()) {
            return false;
        }

        for (Workout w: observableWorkoutList){
            if(w.getWorkoutInput().equals(workout.getWorkoutInput()) && w.getDate().equals(workout.getDate())){
                return true;
            }
        }
        return false;

    }

    private int getAmountWorkouts() {
        TableView<Workout> workoutList = (TableView<Workout>) getRootNode().lookup("#workoutsList");
        ObservableList<Workout> obserbableWorkoutList = workoutList.getItems();
        return obserbableWorkoutList.size();
    }

    private void typeString(String string){ 
        String keys[] = string.split("");
        for (String i : keys){
            KeyCode key = KeyCode.getKeyCode(i.toUpperCase());
            robot.press(key);
            robot.release(key);
        }
    }

    private void pickDate(LocalDate date) {
        interact(() -> {
            DatePicker datePicker = (DatePicker) getRootNode().lookup("#inputDate");
            datePicker.setValue(date);
        });
    }

    private void registerWorkout(String s){
        clickOn("#inputWorkout" );
        typeString(s);
        pickDate(LocalDate.now());
        clickOn("#registerButton");
    }

    private void registerWorkout(String s, LocalDate date){
        clickOn("#inputWorkout" );
        typeString(s);
        pickDate(date);
        clickOn("#registerButton");
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("provideWorkoutData")
    public void testRegisterButton(String input, LocalDate expectedDate){
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

    @Test
    @Order(2)
    public void testRegiserButtonWhenEmptyInputField(){
        int workoutLogSize = getAmountWorkouts();
        clickOn("#registerButton");
        assertEquals(workoutLogSize, getAmountWorkouts());
    }
    
    @Test
    @Order(3)
    public void testHandleEdit(){

        while (getAmountWorkouts() < 2){
            registerWorkout("swimming");
        }

        Workout originalLatestWorkout = getLatestWorkout();
        //get the row to click on
        int workoutLogSize = getAmountWorkouts();
        Node lastCell = lookup("#workoutsList .table-row-cell").nth(workoutLogSize - 1).lookup(".table-cell:nth-child(1)").query();
        doubleClickOn(lastCell);

        //insert change
        clickOn("#inputWorkout");
        typeString("change");
        clickOn("#registerButton");

        //check that change was correct
        Workout changedWorkout = new Workout(originalLatestWorkout.getWorkoutInput() + "change", originalLatestWorkout.getDate());
        assertTrue(workoutLogContainsWorkout(changedWorkout));

        //doing the same for editing a workout using the button
        originalLatestWorkout = getLatestWorkout();
        workoutLogSize = getAmountWorkouts();
        lastCell = lookup("#workoutsList .table-row-cell").nth(workoutLogSize - 1).lookup(".table-cell:nth-child(1)").query();
        clickOn(lastCell);
        clickOn("#editButton");

        clickOn("#inputWorkout");
        typeString("change");
        clickOn("#registerButton");

        changedWorkout = new Workout(originalLatestWorkout.getWorkoutInput() + "change", originalLatestWorkout.getDate());
        assertTrue(workoutLogContainsWorkout(changedWorkout));
    }

    @Test
    @Order(4)
    public void testHandleEditIfInputFieldNotEmpty(){
        if (getAmountWorkouts() == 0) { //added sample workout for when the log is empty
            registerWorkout("editthis");
        }

        clickOn("#inputWorkout");
        typeString("placeholder");

        int workoutLogSize = getAmountWorkouts();
        Node lastCell = lookup("#workoutsList .table-row-cell").nth(workoutLogSize - 1).lookup(".table-cell:nth-child(1)").query();
        doubleClickOn(lastCell);

        clickOn("#inputWorkout" );
        typeString("addchange");
        pickDate(LocalDate.now());
        clickOn("#registerButton");

        assertEquals(getLatestWorkout().toString(), (new Workout("placeholderaddchange").toString()));
    }

    @Test
    @Order(5)
    public void testHandleDelete() {
        if (getAmountWorkouts() == 0) { //added sample workout for when the log is empty
            registerWorkout("editthis");
        }

        int workoutLogSize = getAmountWorkouts();
        Node lastCell = lookup("#workoutsList .table-row-cell").nth(workoutLogSize - 1).lookup(".table-cell:nth-child(1)").query();
        clickOn(lastCell);
        clickOn("#deleteButton");
        
        assertEquals(workoutLogSize - 1, getAmountWorkouts()); //verifies that size has decreased by 1
    }

    @Test
    @Order(6)
    public void testHandleDeleteIfNoWorkoutSelected() {
        if (getAmountWorkouts() == 0) { //added sample workout for when the log is empty
            registerWorkout("editthis");
        }
        int workoutLogSize = getAmountWorkouts();
        clickOn("#deleteButton");
        assertEquals(workoutLogSize, getAmountWorkouts());
    }

    @Test
    @Order(7)
    public void testHandleClear() {
        while (getAmountWorkouts() < 2){
            registerWorkout("running");
        }
        clickOn("#clearAllButton");
        assertEquals(0, getAmountWorkouts());

        while (getAmountWorkouts() < 2){
            registerWorkout("running");
        }
    }

    @Test
    @Order(8)
    public void testRegisterWithInvalidDate() {
        if (getAmountWorkouts() == 0) { //added sample workout for when the log is empty
            registerWorkout("tabata", LocalDate.of(2024, 10, 13));
            registerWorkout("hiit", LocalDate.of(2024, 10, 24));
        }

        registerWorkout("cardio", LocalDate.of(2025, 10, 10));
        Label errorLabel = (Label) getRootNode().lookup("#errorLabel");
        assertEquals("Date can not be in the future", errorLabel.getText());
        Workout originalLatestWorkout = getLatestWorkout();
        assertNotEquals(LocalDate.of(2025, 10, 10), originalLatestWorkout.getDate());
    }
    }
