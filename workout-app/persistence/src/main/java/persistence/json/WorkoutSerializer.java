package persistence.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Workout;
import java.io.IOException;

/**
 * Serializer to convert Workout object to JSON.
 */
public class WorkoutSerializer extends JsonSerializer<Workout> {

  /*Format:
    * {
    *      "workoutInput": "....",
    *      "date": "...."
    * }
    */

  @Override
  public void serialize(Workout workout,
                        JsonGenerator jsonGen,
                        SerializerProvider serializerProvider) throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeStringField("workoutInput", workout.getWorkoutInput());
    jsonGen.writeStringField("date", workout.getDate().toString());
    jsonGen.writeEndObject();
  }
}
