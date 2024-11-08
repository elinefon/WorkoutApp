package springboot.workoutApi.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import core.WorkoutLog;
import core.Workout;

@Configuration
public class WorkoutLogConfig {
    
    /**
     * This class initializes a workoutLog with default workouts
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
