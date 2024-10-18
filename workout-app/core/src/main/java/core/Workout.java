package core;

import java.time.LocalDate;

public class Workout {

    private String workoutInput; //only possible input will be a random string for now
    private LocalDate date;

    public Workout(String workoutInput, LocalDate date) {
        this.workoutInput = workoutInput;
        this.date = date;
    }

    //Getters, Setters and toString

    public String getWorkoutInput() {
        return workoutInput;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setWorkoutInput(String workoutInput) {
        this.workoutInput = workoutInput;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Workout [workoutInput=" + workoutInput + ", date=" + date + "]";
    }

}