package springboot.workoutApi.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.Workout;
import springboot.workoutApi.service.WorkoutLogService;


@RestController
public class WorkoutLogController {

    private WorkoutLogService service;

    @Autowired
    public WorkoutLogController(WorkoutLogService service){
        this.service = service;
    }

    @GetMapping("/")
    public List<Workout> getWorkouts(){
        return service.getWorkouts();
    }
    
    @GetMapping("/workout")
    public Workout getWorkout(@RequestParam String workoutInput){
        Optional<Workout> workout = service.getWorkout(workoutInput);
        if(workout.isPresent()){
            return workout.get();
        }return null;
    }

    @PostMapping("/workout")
    public Workout addWorkout(@RequestParam String workoutInput, @RequestParam(required=false) LocalDate date){
        Workout workoutAdded = service.addWorkout(workoutInput, date);
        return workoutAdded;
    }


    @DeleteMapping("/workout")
    public String removeWorkout(@RequestParam String workoutInput, @RequestParam(required=false) LocalDate date){
        boolean wasDeleted = service.removeWorkout(workoutInput, date);
        if (wasDeleted){
            return "Successfully removed workout with input: " + workoutInput;
        }
        return "Could not remove workout.";
    }

    
}
