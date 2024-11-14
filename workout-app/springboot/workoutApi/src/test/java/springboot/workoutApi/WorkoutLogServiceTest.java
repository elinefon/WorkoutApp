package springboot.workoutApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Workout;
import core.WorkoutLog;

public class WorkoutLogServiceTest {
  
  private WorkoutLogService service;

  @BeforeEach
  void setup(){
    WorkoutLog workoutLog = new WorkoutLog();

    Workout w1 = new Workout("Arms");
    Workout w2 = new Workout("Legs");

    workoutLog.addWorkout(w2);
    workoutLog.addWorkout(w1);
   
    service = new WorkoutLogService(workoutLog);
  }

  @Test
  void testGetWorkout(){
    Optional<Workout> workoutOp = service.getWorkout("Arms");
    assertTrue(workoutOp.isPresent());
    assertEquals(LocalDate.now(), workoutOp.get().getDate());
    assertThrows(NullPointerException.class, () -> 
        { service.getWorkout("NotHere");}); 
  }

  @Test
  void testGetWorkoutLog(){
    assertEquals(2, service.getWorkouts().size());
  }

  @Test
  void testAddWorkout(){
    assertEquals(2, service.getWorkouts().size());
    service.addWorkout("New", null);
    assertEquals(3, service.getWorkouts().size());
    service.addWorkout("Another", LocalDate.of(22, 5, 12));
    assertTrue(service.getWorkout("Another").isPresent());
  }

  @Test
  void testRemoveWorkout(){
    System.out.println(service.getWorkouts());
    assertEquals(2, service.getWorkouts().size());
    boolean removed = service.removeWorkout("Arms", null);
    assertTrue(removed);
    boolean tryRemoved = service.removeWorkout("Fail", null);
    assertFalse(tryRemoved);
    assertEquals(1, service.getWorkouts().size());
  }


}
