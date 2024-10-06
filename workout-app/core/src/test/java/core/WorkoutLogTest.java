package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class WorkoutLogTest {

    @Test
    public void testAddingWorkout(){
        WorkoutLog wlog = new WorkoutLog();
        assertEquals(wlog.getWorkouts().size(), 0);
        wlog.addWorkout(new Workout("Gå opp trappa"));
        assertEquals(wlog.getWorkouts().size(), 1);
        wlog.addWorkout(new Workout("Jogge opp trappa"));
        assertEquals(wlog.getWorkouts().size(), 2);
    }

}
