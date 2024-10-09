package persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Workout;
import core.WorkoutLog;

public class WorkoutPersistenceTest {

    private WorkoutPersistence workoutPersistence;

    private final String mvnDir = System.getProperty("user.dir");
    private String testFilePath;
    private String testFileName;

    @BeforeEach
    public void setUp() {
        workoutPersistence = new WorkoutPersistence();

        testFileName = "testWorkout.JSON";
        testFilePath = mvnDir + "/../persistence/src/main/resources/persistence/json/";

        workoutPersistence.setFilePath(testFilePath);
        workoutPersistence.setFileName(testFileName);
    }

    @Test
    public void testSaveWorkoutLog() {
        Workout w1 = new Workout("Leg day");
        Workout w2 = new Workout("Chest day");
        WorkoutLog log = new WorkoutLog();
        log.addWorkout(w1);
        log.addWorkout(w2);

        workoutPersistence.saveWorkoutLog(log); //save workout log to file

        WorkoutLog loadedLog = workoutPersistence.loadWorkoutLog(); //load from file

        validateWorkoutLog(loadedLog); // validate saved and loaded logs are the same
    }


    private void validateWorkoutLog(WorkoutLog log) {
        Iterator<Workout> iterator = log.iterator();
        assertTrue(iterator.hasNext());
        checkWorkout(iterator.next(), "Leg day");
        assertTrue(iterator.hasNext());
        checkWorkout(iterator.next(), "Chest day");
        assertFalse(iterator.hasNext());
    }

    static void checkWorkout(Workout workout, String expectedDescription) {
        assertEquals(expectedDescription, workout.getWorkoutInput());
    }

    // @Test
    // public void testLoadWorkoutLog() {
    //     // Arrange: Create and save a sample WorkoutLog
    //     WorkoutLog originalLog = createSampleWorkoutLog();
    //     workoutPersistence.saveWorkoutLog(originalLog);

    //     // Act: Load the WorkoutLog
    //     WorkoutLog loadedLog = workoutPersistence.loadWorkoutLog();

    //     // Assert: Validate that the loaded log matches the original
    //     assertNotNull(loadedLog, "Loaded WorkoutLog should not be null.");
    //     assertEquals(originalLog.getWorkouts().size(), loadedLog.getWorkouts().size(), "Loaded WorkoutLog should contain the same number of workouts.");

    //     // Further assertions can be added here to compare the contents of the WorkoutLog
    //     validateWorkoutLog(originalLog, loadedLog);
    // }

    // private WorkoutLog createSampleWorkoutLog() {
    //     // Create a sample WorkoutLog with dummy data
    //     WorkoutLog log = new WorkoutLog();
    //     log.addWorkout(new Workout("Leg day"));  // Add sample workouts
    //     log.addWorkout(new Workout("Chest day"));
    //     return log;
    // }

    // private void validateWorkoutLog(WorkoutLog originalLog, WorkoutLog loadedLog) {
    //     assertEquals(originalLog.getWorkouts().size(), loadedLog.getWorkouts().size(), "Workout logs should have the same number of workouts.");
    //     for (int i = 0; i < originalLog.getWorkouts().size(); i++) {
    //         assertEquals(originalLog.getWorkouts().get(i).getWorkoutInput(), loadedLog.getWorkouts().get(i).getWorkoutInput(), "Workout names should match.");
    //     }
    // }
}