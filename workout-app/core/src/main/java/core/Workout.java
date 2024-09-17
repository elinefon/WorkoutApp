package core;

public class Workout {
    String description;

    public Workout(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Workout [description=" + description + "]";
    }

}
