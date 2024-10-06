package persistence.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Workout;
import core.WorkoutLog;

public class WorkoutLogSerializer extends JsonSerializer<WorkoutLog> {

    /*Format:
     * {"workouts": [...]}
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
