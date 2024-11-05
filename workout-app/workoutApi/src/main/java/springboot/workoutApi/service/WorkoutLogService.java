package springboot.workoutApi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import springboot.workoutApi.api.model.Workout;
import springboot.workoutApi.api.model.WorkoutLog;


@Service
public class WorkoutLogService {

    private WorkoutLog workoutLog;

    public WorkoutLogService(){
        workoutLog = new WorkoutLog();

        Workout w1 = new Workout("Arms");
        Workout w2 = new Workout("Legs");

        workoutLog.addWorkout(w2);
        workoutLog.addWorkout(w1);

    }

    public Optional<Workout> getWorkout(String workoutInput){
        Optional<Workout> optional = Optional.empty();
        optional = Optional.of(workoutLog.getWorkout(workoutInput));
        return optional;
    }

    public List<Workout> getWorkouts(){
        System.out.println(workoutLog);
        return workoutLog.getWorkouts();
    }

    public boolean removeWorkout(String workoutInput, LocalDate date){
        return workoutLog.removeWorkout(workoutInput, date); 
    }

    public Workout addWorkout(String workoutInput, LocalDate date){
        Workout newWorkout;
        if(date == null){
            newWorkout = new Workout(workoutInput);
        }else{
            newWorkout = new Workout(workoutInput, date);
        }

        workoutLog.addWorkout(newWorkout);
        return newWorkout;
    }
 
}
