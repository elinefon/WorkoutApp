package springboot.workoutApi;

import core.Workout;
import core.WorkoutLog;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service class for managing workout log operations.
 */
@Service
public class WorkoutLogService {

  private WorkoutLog workoutLog;

  public WorkoutLogService(WorkoutLog workoutLog) {
    this.workoutLog = workoutLog;
  }

  /**
   * Gets specific workout from log based on input.
   *
   * @param workoutInput the name of the workout
   * @return Workout object if found, empty optional otherwise
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
   * Adds new workout to the log.
   *
   * @param workoutInput name of the workout to add.
   * @param date optional date to add, today if null
   * @return the new workout
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