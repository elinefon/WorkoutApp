package persistence.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Workout;
import core.WorkoutLog;

/**
 * Serializer to convert WorkoutLog (list of Workout objects) to JSON.
 */
public class WorkoutLogSerializer extends JsonSerializer<WorkoutLog> {

    /*Format:
     * {"workouts": [...]}
     */

    /**
     * @param workoutLog WorkoutLog object to serialize to JSON
     * @param jGen JsonGenerator used to write JSON content
     * @param serializerProvider provider for serializing instances
     * @throws IOException if an error happens while generating JSON
     */
    @Override
    public void serialize(WorkoutLog workoutlog,
                          JsonGenerator jGen,
                          SerializerProvider serializerProvider) throws IOException {
      jGen.writeStartObject();
      jGen.writeArrayFieldStart("workouts");
      
      for(Workout workout: workoutlog){
        jGen.writeObject(workout);
      }
      jGen.writeEndArray();
      jGen.writeEndObject();
    }
  
}
