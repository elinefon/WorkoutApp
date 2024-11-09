package springboot.workoutApi.service;

import core.Workout;
import core.WorkoutLog;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service class for managing workouts.
 */
@Service
public class WorkoutLogService {

  private WorkoutLog workoutLog;

  /**
   * Constructor that initializes log.
   */
  public WorkoutLogService() {
    workoutLog = new WorkoutLog();

    Workout w1 = new Workout("Arms");
    Workout w2 = new Workout("Legs");

    workoutLog.addWorkout(w2);
    workoutLog.addWorkout(w1);
  }

  /**
   * Retrieves a workout based on input.
   *
   * @param workoutInput the input of the workout to be found
   * @return workout if found, empty if there was no match
   */
  public Optional<Workout> getWorkout(String workoutInput) {
    Optional<Workout> optional = Optional.empty();
    optional = Optional.of(workoutLog.getWorkout(workoutInput));
    return optional;
  }

  public List<Workout> getWorkouts() {
    System.out.println(workoutLog);
    return workoutLog.getWorkouts();
  }

  public boolean removeWorkout(String workoutInput, LocalDate date) {
    return workoutLog.removeWorkout(workoutInput, date); 
  }

  /**
   * Adds a workout based on input and possbly date.
   *
   * @param workoutInput the input of the workout to be added
   * @param date the date of the workout to be added
   * @return the newly created workout
   */
  public Workout addWorkout(String workoutInput, LocalDate date) {
    Workout newWorkout;
    if (date == null) {
      newWorkout = new Workout(workoutInput);
    } else {
      newWorkout = new Workout(workoutInput, date);
    }

    workoutLog.addWorkout(newWorkout);
    return newWorkout;
  }
}
