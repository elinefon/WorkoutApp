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

    @Override
    public String toString() {
        return "WorkoutLog [" + workouts + "]";
    }

    //Write and read from file methods
    public List<Workout> loadWorkouts(){
        //If we want to save more files add filename as an attribute
        String filename = "workout-data.txt"; 

        DataHandeler dataHandeler = new DataHandeler();
        List<Workout> returnlist = dataHandeler.loadData(filename);

        return returnlist;
    }

    public void saveWorkout(Workout workout){
        //add this workout to all workouts
        this.addWorkout(workout);
        String filename =  "workout-data.txt"; 

        DataHandeler dataHandeler = new DataHandeler();
        dataHandeler.saveData(filename, this.getWorkouts());
        
    }

    public static void main(String[] args){
        //Testing the persistence functions
        Workout w1 = new Workout("Legs");
        Workout w2 = new Workout("Arm day");

        WorkoutLog WL = new WorkoutLog();
        WL.addWorkout(w1);
        
        System.out.println(WL);
        WL.saveWorkout(w2);

        List<Workout> list = WL.loadWorkouts();
        System.out.println(list + "eee");
    }
    


}