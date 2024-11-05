package springboot.workoutApi.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

public class WorkoutLog implements Iterable<Workout>{
    private List<Workout> workouts; //collects workout objects in a list

    //Constructor
    public WorkoutLog() { 
        this.workouts = new ArrayList<>();
    }

    //Add one workout to existing log
    public boolean addWorkout(Workout workout) { 
        if(workout != null){
            workouts.add(workout);
            return true;
        }return false;
    }
 
    public void removeWorkout(Workout workout) {
        if(workout != null){
            workouts.remove(workout);
        }
    }

    public boolean removeWorkout(String workoutInput, LocalDate date) {
        for(Workout w: workouts){
            if(w.getWorkoutInput().equals(workoutInput) && (date == null || w.getDate().equals(date))){
                workouts.remove(w);
                return true;
            }
        }return false;
    }

    //Getters and Setters
    public void setWorkouts(List<Workout> workouts) {
        this.workouts = new ArrayList<>(workouts);
    }

    
    public List<Workout> getWorkouts() {
        return new ArrayList<Workout>(workouts); //returns copy of workoutlist
    }

    public Workout getWorkout(String workoutInput) {
        for(Workout w: workouts){
            if(w.getWorkoutInput().equals(workoutInput)){
                return w;
            }
        }
        return null;
    }


    //Sort workouts by date
    public void sortByDate() {
        Collections.sort(workouts, Comparator.comparing(Workout::getDate));
    }

    @Override
    public Iterator<Workout> iterator() { //iterator
        return workouts.iterator();
    }

    @Override
    public String toString() {
        return "WorkoutLog [" + workouts + "]";
    }

}

