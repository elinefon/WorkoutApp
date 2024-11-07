package core;

import java.time.LocalDate;

/**
 * Core data of a single workout
 */
public class Workout {

    private String workoutInput;
    private LocalDate date;

    /**
     * This constructor is for when the user specifies the date in their workout.
     * @param workoutInput input written by user
     * @param date date chosen by user
     */
    public Workout(String workoutInput, LocalDate date) {
        this.workoutInput = workoutInput;
        this.date = date;
    }

    /**
     * This constructor is for when the user does not specify the date. It will then choose today's date.
     * @param workoutInput input written by user
     */
    public Workout(String workoutInput) {
        this.workoutInput = workoutInput;
        this.date = LocalDate.now();
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