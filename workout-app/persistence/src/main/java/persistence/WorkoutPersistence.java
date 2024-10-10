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

import com.fasterxml.jackson.databind.ObjectMapper;

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

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //This method read a workout from the file given. This is never called whitin this module
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
    
    //This method saves a workout from the file given. This is never called whitin this module
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
