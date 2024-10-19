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
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

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
        String fxid = "#workouts_list";
        TableView<Workout> workout_list = (TableView<Workout>) getRootNode().lookup(fxid);
        ObservableList<Workout> obserbable_workout_list = workout_list.getItems();
        return obserbable_workout_list.get(obserbable_workout_list.size() - 1);
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

    @ParameterizedTest
    @ValueSource(strings = {"cardio", "leggs", "core"})
    public void test_register_button(String input){
        Workout original_latest_workout = get_latest_workout();
        clickOn("#input_workout" );
        type_string(input);
        clickOn("#register_button");
        Workout new_latest_workout = get_latest_workout();
        assertNotEquals(original_latest_workout, new_latest_workout);
        assertEquals(new_latest_workout.toString(), (new Workout(input)).toString());
    }

    @Test
    public void testEditing(){
        Workout original_latest_workout = get_latest_workout();

        int workoutLogSize = getAmoutWorkouts();
        Node lastRow = lookup("#workouts_list .table-row-cell").nth(workoutLogSize-1).query();
        doubleClickOn(lastRow);
      
        clickOn("#input_workout" );
        type_string("change");
        clickOn("#register_button");

        Workout new_latest_workout = get_latest_workout();
        System.out.println(original_latest_workout + ", " + new_latest_workout);
        assertNotEquals(original_latest_workout, new_latest_workout);
        assertEquals(new_latest_workout.toString(), (new Workout("corechange")).toString());
    }
}
