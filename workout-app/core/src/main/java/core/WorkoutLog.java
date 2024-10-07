package core;

import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;


public class WorkoutLog implements Iterable<Workout> { //log that iterates over workouts
    
    private List<Workout> workouts; //collects workout objects in a list

    public WorkoutLog() { //constructor initiates the list of workouts
        this.workouts = new ArrayList<>();
    }

    public void addWorkout(Workout workout) { //adds a workout to the log
        workouts.add(workout);
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
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