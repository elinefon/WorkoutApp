package persistence.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Workout;
import core.WorkoutLog;
import java.io.IOException;

/**
 * Serializer to convert WorkoutLog (list of Workout objects) to JSON.
 */

public class WorkoutLogSerializer extends JsonSerializer<WorkoutLog> {

  /*Format:
   * {"workouts": [...]}
   */

  /**
   * Serializer to convert WorkoutLog (list of Workout objects) to JSON.
   *
   * @param workoutLog WorkoutLog object to serialize to JSON
   * @param jsonGen JsonGenerator used to write JSON content
   * @param serializerProvider provider for serializing instances
   * 
   * @throws IOException if an error happens while generating JSON
   */
  @Override
  public void serialize(WorkoutLog workoutLog, JsonGenerator jsonGen, 
      SerializerProvider serializerProvider) throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeArrayFieldStart("workouts");
   
    for (Workout workout : workoutLog) {
      jsonGen.writeObject(workout);
    }
    jsonGen.writeEndArray();
    jsonGen.writeEndObject();
  }
}
