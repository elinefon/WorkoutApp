package springboot.workoutApi;

import core.Workout;
import core.WorkoutLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for initialzing WorkoutLog with default workouts.
 */
@Configuration
public class WorkoutLogConfig {
  
  /**
   * Creates WorkoutLog with default workouts,
   * with two example workouts.
   *
   * @return default workoutLog
   */
  @Bean
  public WorkoutLog workoutLog() {
    WorkoutLog workoutLog = new WorkoutLog();

    Workout w1 = new Workout("Arms");
    Workout w2 = new Workout("Legs");

    workoutLog.addWorkout(w2);
    workoutLog.addWorkout(w1);
    return workoutLog;
  }
}
