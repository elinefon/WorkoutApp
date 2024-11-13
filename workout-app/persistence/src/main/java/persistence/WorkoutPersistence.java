package persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Workout;
import core.WorkoutLog;
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
import persistence.json.WorkoutModule;

/**
 * Handles persistence operations on workout data, allowing reading and writing
 * Workout and WorkoutLog objects to and from JSON.
 */
public class WorkoutPersistence {
  
  //Variables to find the path
  private String mvnDir = System.getProperty("user.dir");
  private String userHome = System.getProperty("user.home");
  private String filePath = mvnDir + "/../persistence/src/main/resources/persistence/json/";
  private boolean savingHome = false;

  private ObjectMapper mapper;

  public WorkoutPersistence() {
    mapper = new ObjectMapper();
    mapper.registerModule(new WorkoutModule()); //the mapper is connected to the WorkoutModule
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Converts JSON string into Workout object.
   *
   * @param value JSON string prepresenting a Workout
   * @return deserialized Workout object
   * @throws IllegalStateException if deserialization fails
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
   * Converts JSON string into WorkoutLog object.
   *
   * @param value JSON string representing a WorkoutLog
   * @return deserialized WorkoutLog object
   * @throws IllegalStateException if deserialization fails
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
   * Serializes a Workout object to JSON string.
   *
   * @param workout Workout object to be serialized
   * @return JSON string representing Workout
   * @throws IllegalStateException if serialization fails
   */
  @SuppressWarnings("exports")
  public String writeWorkoutAsJson(Workout workout) {
    try {
      return mapper.writeValueAsString(workout);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Issue with writing the workout object as json: "
        + e.getMessage());
    }
  }

  /**
   * Reads a WorkoutLog from the file given. This is never called whitin this module
   *
   * @param fileName name of JSON file containing log data
   * @return deserialized WorkoutLog object 
   */
  @SuppressWarnings("exports")
  public WorkoutLog loadWorkoutLog(String fileName) {
    try (Reader reader = new InputStreamReader(new FileInputStream(filePath + fileName),
        StandardCharsets.UTF_8)) {
      return mapper.readValue(reader, WorkoutLog.class);
    } catch (FileNotFoundException e) {
      System.err.println("File not found");

      WorkoutLog newLog = new WorkoutLog();
      saveWorkoutLog(newLog, fileName);
      savingHome = true;
      return newLog;
    } catch (IOException e) {
      throw new IllegalArgumentException("File could not be loaded", e);
    }
  }
  
  /**
   * Saves a WorkoutLog from the file given. This is never called whitin this module.
   *
   * @param workoutLog WorkoutLog object to be saved
   * @param fileName the file name to save the workout log in
   */
  @SuppressWarnings("exports")
  public void saveWorkoutLog(WorkoutLog workoutLog, String fileName) {
  //Ensures there is a directory for the specified path
  if (savingHome) {
    saveWorkoutLogUserHome(workoutLog, fileName);
    return;
  }
    try {
      Files.createDirectories(Paths.get(filePath));
    } catch (IOException e) {
      System.err.println("Error while creating directories: " + e.getMessage());
    }
    //Writes to file
    try (Writer writer = new FileWriter(filePath + fileName, StandardCharsets.UTF_8)) {
      mapper.writerWithDefaultPrettyPrinter().writeValue(writer, workoutLog);
    } catch (IOException e) {
      System.err.println("Error while saving the workout log: " + e.getMessage());
  }
  }

    /**
   * Saves a WorkoutLog from the file given. This is never called whitin this module.
   *
   * @param workoutLog WorkoutLog object to be saved
   * @param fileName the file name to save the workout log in
   */
  @SuppressWarnings("exports")
  public void saveWorkoutLogUserHome(WorkoutLog workoutLog, String fileName) {
  //Ensures there is a directory for the specified path
  System.out.println("hei");
    try {
      Files.createDirectories(Paths.get(userHome));
    } catch (IOException e) {
      System.err.println("Error while creating directories: " + e.getMessage());
    }
    //Writes to file
    try (Writer writer = new FileWriter(userHome + "/" + fileName, StandardCharsets.UTF_8)) {
      mapper.writerWithDefaultPrettyPrinter().writeValue(writer, workoutLog);
    } catch (IOException e) {
      System.err.println("Error while saving the workout log: " + e.getMessage());
  }
  }
}
