package core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * List of items in a workout log.
 */
public class WorkoutLog implements Iterable<Workout> {

  private List<Workout> workouts;

  /**
 * Constructor specifies the list of workouts.
 */
  public WorkoutLog() {
    this.workouts = new ArrayList<>();
  }

  /**
 * Adds provided workout to the log.
 *
 * @param workout the Workout to add
 * @return boolean value true if workout was successfully added, false otherwise
 */
  public boolean addWorkout(Workout workout) {
    if (workout != null) {
      workouts.add(workout);
      return true;
    }
    return false;
  }

  /**
 * Method to remove workout based on the whole workout object.
 *
 * @param workout the Workout to remove from the log
 */
  public void removeWorkout(Workout workout) {
    if (workout != null) {
      workouts.remove(workout);
    }
  }

  /**
 * Method to remove workout based on the input and date.
 *
 * @param workoutInput the input for the workout
 * @param date the date for the workout
 * @return boolean value, true if successfully removed, false otherwise
 */
  public boolean removeWorkout(String workoutInput, LocalDate date) {
    for (Workout w : workouts) {
      if (w.getWorkoutInput().equals(workoutInput) && (date == null || w.getDate().equals(date))) {
        workouts.remove(w);
        return true;
      }
    }
    return false;
  }

  /**
 * For workouts in the log, the workouts will be sorted by the date chosen,
 * and not when the workout was created.
 */
  public void sortByDate() {
    Collections.sort(workouts, Comparator.comparing(Workout::getDate));
  }

  @Override
  public Iterator<Workout> iterator() {
    return workouts.iterator();
  }

  // Getters, setters and toString
  public void setWorkouts(List<Workout> workouts) {
    this.workouts = new ArrayList<>(workouts);
  }

  public List<Workout> getWorkouts() {
    return new ArrayList<Workout>(workouts); // returns copy of workoutlist
  }

  /**
 * Finds a workout using input.
 *
 * @param workoutInput the input the workout will be found by.
 * @return the workout corresponding to the input, null otherwise.
 */
  public Workout getWorkout(String workoutInput) {
    for (Workout w : workouts) {
      if (w.getWorkoutInput().equals(workoutInput)) {
        return w;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "WorkoutLog [" + workouts + "]";
  }
}
