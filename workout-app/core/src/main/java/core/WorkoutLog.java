package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;


public class WorkoutLog implements Iterable<Workout> { //log that iterates over workouts
    
    private List<Workout> workouts; //collects workout objects in a list

    //Constructor
    public WorkoutLog() { 
        this.workouts = new ArrayList<>();
    }


    //Add one workout to existing log
    public void addWorkout(Workout workout) { 
        workouts.add(workout);
    }

    //Getters and Setters
    public void setWorkouts(List<Workout> workouts) {
        this.workouts = new ArrayList<>(workouts);
    }

    
    public List<Workout> getWorkouts() {
        return new ArrayList<Workout>(workouts); //returns copy of workoutlist
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