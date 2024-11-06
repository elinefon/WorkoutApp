package persistence;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Workout;
import core.WorkoutLog;
import persistence.json.WorkoutModule;

public class WorkoutModuleTest {

    private static ObjectMapper mapper;
    private final static String expectedString = "{\"workouts\":[" + "{\"workoutInput\":\"Leg day\",\"date\":\"2024-10-11\"}," + "{\"workoutInput\":\"Chest day\",\"date\":\"2024-10-10\"}" + "]}";

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new WorkoutModule());
    }
    
    //helper method to create a new workout, used in multiple methods
    private WorkoutLog createWorkoutLog() {
        Workout w1 = new Workout("Leg day", LocalDate.of(2024, 10, 11));
        Workout w2 = new Workout("Chest day", LocalDate.of(2024, 10, 10));
        WorkoutLog log = new WorkoutLog();
        log.addWorkout(w1);
        log.addWorkout(w2);
        return log;
    }

    //helper method to check deserialized log, used in multiple methods
    private void validateWorkoutLog(WorkoutLog log) {
        Iterator<Workout> iterator = log.iterator();
        assertTrue(iterator.hasNext());
        checkWorkout(iterator.next(), "Leg day");
        checkWorkout(iterator.next(), "Chest day");
        assertFalse(iterator.hasNext());
    }

    //helper method to check workout
    static void checkWorkout(Workout workout, String input) {
        assertEquals(input, workout.getWorkoutInput());
    }

    @Test
    public void testSerializers() {
        WorkoutLog log = createWorkoutLog();
        try {
            assertEquals(expectedString, mapper.writeValueAsString(log));
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testDeserializers() {
        try {
            WorkoutLog log = mapper.readValue(expectedString, WorkoutLog.class);
            validateWorkoutLog(log);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    //serializing object to json and deserializing back to verify deserialized object is the same as the original
    @Test
    public void testSerializersDeserializers() {
        WorkoutLog log = createWorkoutLog();
        try {
            String json = mapper.writeValueAsString(log);
            WorkoutLog log2 = mapper.readValue(json, WorkoutLog.class);
            validateWorkoutLog(log2);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

}
