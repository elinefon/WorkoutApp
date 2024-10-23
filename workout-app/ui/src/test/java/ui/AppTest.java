package ui;
import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        String fxid = "#workoutsList";
        TableView<Workout> workoutList = (TableView<Workout>) getRootNode().lookup(fxid);
        ObservableList<Workout> observableWorkoutList = workoutList.getItems();

        if (observableWorkoutList.isEmpty()) {
            return null;
        }
        return observableWorkoutList.get(observableWorkoutList.size() - 1);
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
        Workout originalLatestWorkout = getLatestWorkout();
        registerWorkout(input, expectedDate);
        Workout newLatestWorkout = getLatestWorkout();
        Workout expectedWorkout = new Workout(input, expectedDate);
        assertNotEquals(originalLatestWorkout, newLatestWorkout);
        assertEquals(newLatestWorkout.toString(), expectedWorkout.toString());
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
    
 /*   @Test
    @Order(3)
    public void testHandleEdit(){
        if (getAmountWorkouts() == 0) { //added sample workout for when the log is empty
            registerWorkout("editthis");
            registerWorkout("anotherworkout");
        }

        Workout originalLatestWorkout = getLatestWorkout();
        //get the row to click on
        int workoutLogSize = getAmountWorkouts();
        Node lastRow = lookup("#workoutsList .table-row-cell").nth(workoutLogSize-1).query();
        doubleClickOn(lastRow);

        //insert change
        clickOn("#inputWorkout");
        typeString("change"+ workoutLogSize);
        clickOn("#registerButton");

        //check that change was correct
        Workout newLatestWorkout = getLatestWorkout();
        assertNotEquals(originalLatestWorkout, newLatestWorkout);
        assertEquals(newLatestWorkout.toString(), (new Workout(originalLatestWorkout.getWorkoutInput() + "change")).toString());
    }*/

    @Test
    @Order(4)
    public void testHandleEditIfInputFieldNotEmpty(){
        if (getAmountWorkouts() == 0) { //added sample workout for when the log is empty
            registerWorkout("editthis");
        }

        clickOn("#inputWorkout");
        typeString("placeholder");

        int workoutLogSize = getAmountWorkouts();
        Node lastRow = lookup("#workoutsList .table-row-cell").nth(workoutLogSize-1).query();
        doubleClickOn(lastRow);

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

        int originalSize = getAmountWorkouts();
        Node lastRow = lookup("#workoutsList .table-row-cell").nth(originalSize - 1).query();
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
