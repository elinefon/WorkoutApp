package core;

import java.util.ArrayList;
import java.util.List;

public class WorkoutLog {
    
    private List<Workout> workouts;

    public WorkoutLog(){
        workouts = new ArrayList<>();

    }
    //Get and set methods
    public void addWorkout(Workout workout){
        workouts.add(workout);
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    //Write and read from file methods
    public List<Workout> loadWorkouts(){
        //Per now there is only one file to collect data from.
        String filename = "workout-data.txt"; 

        DataHandeler dataHandeler = new DataHandeler();
        List<Workout> returnlist = dataHandeler.loadData(filename);

        return returnlist;

    }

    public void saveWorkout(Workout workout){
        //add this workout to all workouts
        this.addWorkout(workout);
        String filename = "workout-data.txt"; 

        DataHandeler dataHandeler = new DataHandeler();
        dataHandeler.saveData(filename, this.getWorkouts());
        
    }

    public static void main(String[] args){
        Workout w1 = new Workout("Legs");
        Workout v2 = new Workout("Arm day");

        
    }
    


}