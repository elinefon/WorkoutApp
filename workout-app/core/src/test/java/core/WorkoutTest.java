package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkoutTest {

    private Workout winput;
    private LocalDate date;

    @BeforeEach
    public void setUp(){
        date = LocalDate.of(2024, 10, 18);
        winput = new Workout("Leg day", date);
    }

    @Test
    public void testConstructor(){
        assertEquals("Leg day", winput.getWorkoutInput());
        assertEquals(date, winput.getDate());
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
    public void testGetDate(){
        LocalDate d = LocalDate.of(2024, 10, 10);
        winput.setDate(d);
        assertEquals(d, winput.getDate());
    }

    @Test
    public void testSetDate(){
        winput.setDate(LocalDate.of(2024, 10, 14));
        assertEquals(LocalDate.of(2024, 10, 14), winput.getDate());
    }

    @Test
    public void testToString(){
        String expected = "Workout [workoutInput=Leg day, date=2024-10-18]";
        assertEquals(expected, winput.toString());
    }
}
