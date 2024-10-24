package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.LocalDate;
import java.util.Arrays;

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
        wlog.addWorkout(new Workout("Gå opp trappa", LocalDate.of(2024, 10, 17)));
        assertEquals(1, wlog.getWorkouts().size());
        wlog.addWorkout(new Workout("Jogge opp trappa", LocalDate.of(2024, 10, 18)));
        assertEquals(2, wlog.getWorkouts().size());
    }

    @Test
    public void testGetWorkouts(){
        List<Workout> wlist = new ArrayList<>();
        wlist.add(new Workout("Joggetur", LocalDate.of(2024, 10, 13)));
        wlist.add(new Workout("Svømmetur", LocalDate.of(2024, 10, 12)));
        wlog.setWorkouts(wlist);
        assertEquals(wlist, wlog.getWorkouts());
    }

    @Test
    public void testSetWorkouts(){
        List<Workout> wlist = new ArrayList<>();
        wlist.add(new Workout("Svømming", LocalDate.of(2024, 10, 8)));
        wlist.add(new Workout("Joggetur", LocalDate.of(2024, 10, 7)));
        wlog.setWorkouts(wlist);
        assertEquals(wlist, wlog.getWorkouts());
    }

    @Test
    public void testRemovingWorkout(){
        Workout w1 = new Workout("Svømming");
        Workout w2 = new Workout("Turn");
        wlog.setWorkouts(Arrays.asList(w1, w2));
        assertEquals(2, wlog.getWorkouts().size());
        wlog.removeWorkout(w1);
        assertEquals(1, wlog.getWorkouts().size());
        wlog.removeWorkout(new Workout("False workout"));
        wlog.removeWorkout(null);
        assertEquals(1, wlog.getWorkouts().size());
    }

    @Test
    public void testIterator(){
        wlog.addWorkout(new Workout("Leg day", LocalDate.of(2024, 10, 4)));
        wlog.addWorkout(new Workout("Tabata", LocalDate.of(2024, 10, 3)));
        Iterator<Workout> iterator = wlog.iterator();
        assertEquals("Leg day", iterator.next().getWorkoutInput());
        assertEquals("Tabata", iterator.next().getWorkoutInput());
    }

    @Test
    public void testSortByDate(){
        Workout boxing = new Workout("Boxing", LocalDate.of(2024, 10, 2));
        Workout hike = new Workout("Hike", LocalDate.of(2024, 10, 1));
        Workout hiit = new Workout("HIIT", LocalDate.of(2024, 10, 4));
        Workout run = new Workout("Run", LocalDate.of(2024, 10, 3));
        
        wlog.addWorkout(boxing);
        wlog.addWorkout(hike);
        wlog.addWorkout(hiit);
        wlog.addWorkout(run);

        wlog.sortByDate();

        assertEquals(hike, wlog.getWorkouts().get(0));
        assertEquals(boxing, wlog.getWorkouts().get(1));
        assertEquals(run, wlog.getWorkouts().get(2));
        assertEquals(hiit, wlog.getWorkouts().get(3));
    }

    @Test
    public void testToString(){
        wlog.addWorkout(new Workout("Joggetur", LocalDate.of(2024, 10, 2)));
        wlog.addWorkout(new Workout("Intervaller", LocalDate.of(2024, 10, 1)));
        String expected = "WorkoutLog [[Workout [workoutInput=Joggetur, date=2024-10-02], Workout [workoutInput=Intervaller, date=2024-10-01]]]";
        assertEquals(expected, wlog.toString());
    }

}
