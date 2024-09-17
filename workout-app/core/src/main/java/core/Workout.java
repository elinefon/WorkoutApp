package core;

public class Workout {

    private String workoutInput; //only possible input will be a random string for now

    public Workout(String workoutInput) { //constructor for one workout
        this.workoutInput = workoutInput;
    }

    //getters og setters

    public String getWorkoutInput() {
        return workoutInput;
    }

    public void setWorkoutInput(String workoutInput) {
        this.workoutInput = workoutInput;
    }

}