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

  /**
   *Format:
   * {
   *      "description": "....",
   *      "date": "...."
   * }
   * 
   * @param workout Workout object to serialize to JSON
   * @param JGen JsonGenerator used to write JSON content
   * @param serializerProvider provider for serializing instances
   * @throws IOException if an error happens while generating JSON
   */
  @Override
  public void serialize(Workout workout,
      JsonGenerator jGen,
      SerializerProvider serializerProvider) throws IOException {
    jGen.writeStartObject();
    jGen.writeStringField("description", workout.getWorkoutInput());
    jGen.writeStringField("date", workout.getDate().toString());
    jGen.writeEndObject();
  }
}
