package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public Iterator<Workout> iterator() { //iterator
        return workouts.iterator();
    }

}