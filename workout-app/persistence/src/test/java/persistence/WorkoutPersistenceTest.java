package persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Workout;
import core.WorkoutLog;

public class WorkoutPersistenceTest {

    private WorkoutPersistence workoutPersistence;

    private final String mvnDir = System.getProperty("user.dir");
    private String testFilePath = mvnDir + "/../persistence/src/main/resources/persistence/json/";
    private String testFileName = "aWorkout.JSON";

    @BeforeEach
    public void setUp() {
        workoutPersistence = new WorkoutPersistence();

        workoutPersistence.setFilePath(testFilePath);
    }

    @AfterEach //deletes files after tests
    public void tearDown() throws IOException {
        Path filePath = Path.of(testFilePath, testFileName);
        Files.deleteIfExists(filePath);
    }

    @Test
    public void testSaveWorkoutLog() {
        WorkoutLog log = createWorkoutLog();

        workoutPersistence.saveWorkoutLog(log, testFileName); 

        Path savedFilePath = Path.of(testFilePath, testFileName);
        assertTrue(Files.exists(savedFilePath), "The workout log file should exist after saving.");
    }


    @Test
    public void testLoadWorkoutLog() {
        WorkoutLog log = createWorkoutLog();

        workoutPersistence.saveWorkoutLog(log, testFileName); //save workout log to file

        WorkoutLog loadedLog = workoutPersistence.loadWorkoutLog(testFileName); //load from file
        validateWorkoutLog(loadedLog); // validate saved and loaded logs are the same
    }

    private WorkoutLog createWorkoutLog() {
        Workout w1 = new Workout("Leg day", LocalDate.of(2024, 10, 2));
        Workout w2 = new Workout("Chest day", LocalDate.of(2024, 10, 1));
        WorkoutLog log = new WorkoutLog();
        log.addWorkout(w1);
        log.addWorkout(w2);
        return log;
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
}