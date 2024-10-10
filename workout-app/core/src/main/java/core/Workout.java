package core;

public class Workout {

    private String workoutInput; //only possible input will be a random string for now

    public Workout(String workoutInput) {
        this.workoutInput = workoutInput;
    }

    //Getters, Setters and toString

    public String getWorkoutInput() {
        return workoutInput;
    }

    public void setWorkoutInput(String workoutInput) {
        this.workoutInput = workoutInput;
    }

    @Override
    public String toString() {
        return "Workout [workoutInput=" + workoutInput + "]";
    }

}