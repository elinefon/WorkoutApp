package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkoutTest {

    private Workout winput;

    @BeforeEach
    public void setUp(){
        winput = new Workout("Leg day");
    }

    @Test
    public void testConstructor(){
        assertEquals("Leg day", winput.getWorkoutInput());
    }

    @Test
    public void testGetWorkoutInput(){
        String workout = "Push day";
        winput.setWorkoutInput(workout);
        assertEquals(workout, winput.getWorkoutInput());
    }

    @Test 
    public void testSetWorkoutInput(){
        winput.setWorkoutInput("Tabata");
        assertEquals("Tabata", winput.getWorkoutInput());
    }

    @Test
    public void testToString(){
        String expected = "Workout [workoutInput=Leg day]";
        assertEquals(expected, winput.toString());
    }
}
