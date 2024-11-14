package persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Workout;
import core.WorkoutLog;
import persistence.json.WorkoutModule;
import persistence.WorkoutPersistence;

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

        String homeDirectory = System.getProperty("user.home") + "/";

        (new File(testFilePath + testFileName)).delete();
        (new File(homeDirectory + testFileName)).delete();

        WorkoutLog log2 = workoutPersistence.loadWorkoutLog(testFileName);
        System.out.println(log2.getWorkouts().size());
        System.out.println("------------------------------------------------");
        assertEquals(log2.getWorkouts().size(), 0);
        assertTrue((new File(homeDirectory + testFileName).isFile()));

        workoutPersistence.saveWorkoutLogUserHome(log, testFileName);

        assertTrue((new File(homeDirectory + testFileName)).isFile());

        log = workoutPersistence.loadWorkoutLog(testFileName);

        validateWorkoutLog(loadedLog);

    }

    @Test
    public void testReadValueToWorkoutLog(){
        
        String json = "{\n" + //
                        "  \"workouts\" : [ {\n" + //
                        "    \"workoutInput\" : \"legg day\",\n" + //
                        "    \"date\" : \"2024-11-01\"\n" + //
                        "  } ]\n" + //
                        "}";

        WorkoutLog log1 = workoutPersistence.readValueToWorkoutLog(json);

        WorkoutLog log2 = new WorkoutLog();
        LocalDate date = LocalDate.of(2024, 11, 1);
        log2.addWorkout(new Workout("legg day", date));

        assertEquals(log2.getWorkouts().get(0).getWorkoutInput(), log1.getWorkouts().get(0).getWorkoutInput());



    }

    @Test
    public void testReadValueToWorkout(){
        LocalDate date = LocalDate.of(1998, 1, 1);
        String workoutInput = "cardio";

        Workout workout1 = new Workout(workoutInput, date);
        
        String json = "{\n" + //
                        "    \"workoutInput\" : \"cardio\",\n" + //
                        "    \"date\" : \"1998-01-01\"\n" + //
                        "  }";

        String corruptedJson = "\n" + //
                        "    \"workoutInput\"  \"cardio\",\n" + //
                        "    \"date\" : \"1998-01-01\"\n" + //
                        "  }";

        Workout workout2 = workoutPersistence.readValueToWorkout(json);

        assertEquals(workout1.getDate(), workout2.getDate());
        assertEquals(workout1.getWorkoutInput(), workout2.getWorkoutInput());

        Workout workout3 = workoutPersistence.readValueToWorkout(corruptedJson);
        
        assertEquals(workout3, null);
    }

    @Test
    public void testWriteWorkoutAsJson(){
        String workoutInput = "situps";
        LocalDate date = LocalDate.of(1998, 1, 1);

        Workout workout1 = new Workout(workoutInput, date);

        String json = workoutPersistence.writeWorkoutAsJson(workout1);

        Workout workout2 = workoutPersistence.readValueToWorkout(json);

        assertEquals(workout1.getDate(), workout1.getDate());
        assertEquals(workout1.getWorkoutInput(), workout2.getWorkoutInput());
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

