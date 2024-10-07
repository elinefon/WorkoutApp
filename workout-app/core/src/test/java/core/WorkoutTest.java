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
        Workout cworkout = new Workout("HIIT");
        assertEquals("HIIT", cworkout.getWorkoutInput());
    }

    @Test
    public void testGetWorkoutInput(){
        String workout = "Leg day";
        assertEquals(workout, winput.getWorkoutInput());
    }

    @Test 
    public void testSetWorkoutInput(){
        winput.setWorkoutInput("Tabata");
        assertEquals("Tabata", winput.getWorkoutInput());
    }
}
