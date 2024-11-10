package springboot.workoutApi.api.controller;

import core.Workout;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springboot.workoutApi.service.WorkoutLogService;

/**
 * Controller for the API.
 */
@RestController
public class WorkoutLogController {

  private WorkoutLogService service;

  @Autowired
  public WorkoutLogController(WorkoutLogService service) {
    this.service = service;
  }

  @GetMapping("/")
  public List<Workout> getWorkouts() {
    return service.getWorkouts();
  }
  
  /**
   * Retrieves workout given an input.
   *
   * @param workoutInput the input that corresponds to workout
   * @return workout if found, null otherwise
   */
  @GetMapping("/workout")
  public Workout getWorkout(@RequestParam String workoutInput) {
    Optional<Workout> workout = service.getWorkout(workoutInput);
    if (workout.isPresent()) {
      return workout.get();
    }
    return null;
  }

    private String convertURItoInput(String input){
        return input.replace("_", " ");
    }

    @GetMapping("/")
    public List<Workout> getWorkouts(){
        return service.getWorkouts();
    }
    
    @GetMapping("/workout")
    public Workout getWorkout(@RequestParam String workoutInput){
        String workoutInputStr = convertURItoInput(workoutInput);
        Optional<Workout> workout = service.getWorkout(workoutInputStr);
        if(workout.isPresent()){
            return workout.get();
        }return null;
    }

    @PostMapping("/workout")
    public Workout addWorkout(@RequestParam String workoutInput, @RequestParam(required=false) LocalDate date){
        String workoutInputStr = convertURItoInput(workoutInput);
        Workout workoutAdded = service.addWorkout(workoutInputStr, date);
        return workoutAdded;
    }


    @DeleteMapping("/workout")
    public String removeWorkout(@RequestParam String workoutInput, @RequestParam(required=false) LocalDate date){
        String workoutInputStr = convertURItoInput(workoutInput);
        boolean wasDeleted = service.removeWorkout(workoutInputStr, date);
        if (wasDeleted){
            return "Successfully removed workout with input: " + workoutInputStr;
        }
        return "Could not remove workout.";
    }

    
}
