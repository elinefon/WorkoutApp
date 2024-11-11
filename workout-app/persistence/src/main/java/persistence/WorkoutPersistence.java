package persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Workout;
import core.WorkoutLog;
import persistence.json.WorkoutModule;

public class WorkoutPersistence {
    
    private String mvnDir = System.getProperty("user.dir"); //these are variables to find the path
    private String filePath = mvnDir + "/../persistence/src/main/resources/persistence/json/";

    private ObjectMapper mapper;

    public WorkoutPersistence() {
        mapper = new ObjectMapper();
        mapper.registerModule(new WorkoutModule()); //the mapper is connected to the WorkoutModule
    }
    /**
     * Sets the filepath where for json file
     * @param filePath The new filepath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Uses a string to create a workout object
     * @param value The string used to create the Workout object
     * @return The created Workout object
     */
    @SuppressWarnings("exports")
    public Workout readValueToWorkout(String value) {
        try {
            return mapper.readValue(value, Workout.class);
        } catch (JsonMappingException e) {
            throw new IllegalStateException("Issue with creating workout of json: " + e.getMessage());
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Issue with creating workout of json: " + e.getMessage());
        }
    } 
    /**
     * Uses a string to create a WorkoutLog
     * @param value The string used to create the WorkoutLog
     * @return The WorkoutLog created from the string
     */
    @SuppressWarnings("exports")
    public WorkoutLog readValueToWorkoutLog(String value) {
        try {
            return mapper.readValue(value, WorkoutLog.class);
        } catch (JsonMappingException e) {
            throw new IllegalStateException("Issue with creating workout of json: " + e.getMessage());
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Issue with creating workout of json: " + e.getMessage());
        }
    }   
    /**
     * Represents a workout object in json format
     * @param workout The Workout being represented as json
     * @return A json string representing the Workout
     */
    @SuppressWarnings("exports")
    public String writeWorkoutAsJson(Workout workout){
        try {
            return mapper.writeValueAsString(workout);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Issue with writing the workout object as json: " + e.getMessage());
        }
    }

    /**
     * This method read a workout from the file given. This is never called whitin this module
     * @param fileName the location of the file from which the WorkoutLog should be loaded
     * @return The WorkoutLog stored in the file
     */
    @SuppressWarnings("exports")
    public WorkoutLog loadWorkoutLog(String fileName) {
        //reads from file
        try (Reader reader = new InputStreamReader(new FileInputStream(filePath + fileName), StandardCharsets.UTF_8)) {
            return mapper.readValue(reader, WorkoutLog.class);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("File could not be loaded", e);
        }
    }
    
    /**
     * This method saves a workout from the file given. This is never called whitin this module
     * @param workoutLog The WorkoutLog being saved to a file
     * @param fileName The location of the file where the WorkoutLog is being saved
     */
    @SuppressWarnings("exports")
    public void saveWorkoutLog(WorkoutLog workoutLog, String fileName) {

        //ensures there is an directory for the spesified path
        try {
            Files.createDirectories(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Error while creating directories: " + e.getMessage());
        }

        //writes to file
        try (Writer writer = new FileWriter(filePath + fileName, StandardCharsets.UTF_8)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, workoutLog);
        } catch (IOException e) {
            System.err.println("Error while saving the workout log: " + e.getMessage());
        }
    }

}
