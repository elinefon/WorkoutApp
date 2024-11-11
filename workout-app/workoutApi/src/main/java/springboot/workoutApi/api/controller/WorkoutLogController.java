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
 * REST controller for handling workout log operations.
 * Provides endpoints to get, add and delete workouts.
 */
@RestController
public class WorkoutLogController {

  private WorkoutLogService service;

  @Autowired
  public WorkoutLogController(WorkoutLogService service) {
    this.service = service;
  }

  private String convertUritoInput(String input) {
    return input.replace("_", " ");
  }

  @GetMapping("/")
  public List<Workout> getWorkouts() {
    return service.getWorkouts();
  }
  
  /**
   * Gets workout based on given input.
   *
   * @param workoutInput the input name of the workout
   * @return the corresponding Workout if found, null otherwise
   */
  @GetMapping("/workout")
  public Workout getWorkout(@RequestParam String workoutInput) {
    String workoutInputStr = convertUritoInput(workoutInput);
    Optional<Workout> workout = service.getWorkout(workoutInputStr);
    if (workout.isPresent()) {
      return workout.get();
    }
    return null;
  }

  /**
   * Adds a new workout to the log.
   *
   * @param workoutInput the name of the workout to be added
   * @param date the optional date for the workout (today if nothing specified)
   * @return Workout object added to the log
   */
  @PostMapping("/workout")
  public Workout addWorkout(@RequestParam String workoutInput,
      @RequestParam(required = false) LocalDate date) {
    String workoutInputStr = convertUritoInput(workoutInput);
    Workout workoutAdded = service.addWorkout(workoutInputStr, date);
    return workoutAdded;
  }

  /**
   * Removes workout from the log.
   *
   * @param workoutInput name of the workout to be removed
   * @param date optional date of the workout to be removed
   * @return message indicating either success or fail
   */
  @DeleteMapping("/workout")
  public String removeWorkout(@RequestParam String workoutInput,
      @RequestParam(required = false) LocalDate date) {
    String workoutInputStr = convertUritoInput(workoutInput);
    boolean wasDeleted = service.removeWorkout(workoutInputStr, date);
    if (wasDeleted) {
      return "Successfully removed workout with input: " + workoutInputStr;
    }
    return "Could not remove workout.";
  }
}