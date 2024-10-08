package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkoutLogTest {

    private WorkoutLog wlog;

    @BeforeEach
    public void setUp(){
        wlog = new WorkoutLog();
    }

    @Test
    public void testConstructor(){
        assertNotNull(wlog.getWorkouts());
        assertTrue(wlog.getWorkouts().isEmpty());
    }

    @Test
    public void testAddingWorkout(){
        assertEquals(0, wlog.getWorkouts().size());
        wlog.addWorkout(new Workout("Gå opp trappa"));
        assertEquals(1, wlog.getWorkouts().size());
        wlog.addWorkout(new Workout("Jogge opp trappa"));
        assertEquals(2, wlog.getWorkouts().size());
    }

    @Test
    public void testGetWorkouts(){
        List<Workout> wlist = new ArrayList<>();
        wlist.add(new Workout("Joggetur"));
        wlist.add(new Workout("Svømmetur"));
        wlog.setWorkouts(wlist);
        assertEquals(wlist, wlog.getWorkouts());
    }

    @Test
    public void testSetWorkouts(){
        List<Workout> wlist = new ArrayList<>();
        wlist.add(new Workout("Svømming"));
        wlist.add(new Workout("Joggetur"));
        wlog.setWorkouts(wlist);
        assertEquals(wlist, wlog.getWorkouts());
    }

    @Test
    public void testIterator(){
        wlog.addWorkout(new Workout("Leg day"));
        wlog.addWorkout(new Workout("Tabata"));
        Iterator<Workout> iterator = wlog.iterator();
        assertEquals("Leg day", iterator.next().getWorkoutInput());
        assertEquals("Tabata", iterator.next().getWorkoutInput());
    }

    @Test
    public void testToString(){
        wlog.addWorkout(new Workout("Joggetur"));
        wlog.addWorkout(new Workout("Intervaller"));
        String expected = "WorkoutLog [[Workout [workoutInput=Joggetur], Workout [workoutInput=Intervaller]]]";
        assertEquals(expected, wlog.toString());
    }


}
