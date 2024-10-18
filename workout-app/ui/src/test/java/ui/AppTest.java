package ui;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import core.Workout;
import core.WorkoutLog;

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
        //return ((Label) getRootNode().lookup("#operandView")).;
        String fxid = "#workouts_list";
        TableView<Workout> workout_list = (TableView<Workout>) getRootNode().lookup(fxid);
        ObservableList<Workout> observable_workout_list = workout_list.getItems();

        if (observable_workout_list.isEmpty()) {
            return null;
        }
        return observable_workout_list.get(observable_workout_list.size() - 1);


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
    //@ValueSource(strings = {"cardio", "leggs", "core"})
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
            Arguments.of("leggs", LocalDate.of(2024, 10, 12)),
            Arguments.of("core", LocalDate.of(2024, 10, 13))
        );
    }
}
