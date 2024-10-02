package persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.Workout;
import core.WorkoutLog;
import persistence.json.WorkoutModule;

public class WorkoutPersistence {
    
    private String mvnDir = System.getProperty("user.dir");
    private String filePath = mvnDir + "/../persistence/src/main/resources/persistence/json/";
    private String fileName = "myWorkout.JSON";

    private ObjectMapper mapper;

    public WorkoutPersistence() {
        mapper.registerModule(new WorkoutModule());
    }

    public WorkoutLog loadWorkoutLog() {
        try (Reader reader = new InputStreamReader(new FileInputStream(filePath + fileName))) {
            return mapper.readValue(reader, WorkoutLog.class);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("File could not be loaded", e);
        }
    }

    public void saveWorkoutLog(WorkoutLog workoutLog) {
        System.out.println(filePath + fileName);
        try (Writer writer = new FileWriter(filePath + fileName)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, workoutLog);
        } catch (IOException e) {
            System.err.println("Error while saving the workout log: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        WorkoutPersistence persistence = new WorkoutPersistence();
        WorkoutLog log = new WorkoutLog();
        Workout workout = new Workout("yay");
        Workout w2 = new Workout("hey");
        
        log.addWorkout(workout);
        log.addWorkout(w2);
        persistence.saveWorkoutLog(log);
    }
}
