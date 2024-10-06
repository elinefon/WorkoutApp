package persistence.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Workout;

public class WorkoutSerializer extends JsonSerializer<Workout> {

    /*Format:
     * {
     *      "description": "...."
     * }
     */
    
  
    @Override
    public void serialize(Workout workout,
                          JsonGenerator jGen,
                          SerializerProvider serializerProvider) throws IOException {
      jGen.writeStartObject();
      jGen.writeStringField("description", workout.getWorkoutInput());
      jGen.writeEndObject();
    }
  }
