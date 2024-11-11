package persistence.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import core.Workout;
import core.WorkoutLog;
import java.io.IOException;

/**
 * Deserializer to convert JSON data into a WorkoutLog object (list of Workout
 * objects).
 */
public class WorkoutLogDeserializer extends JsonDeserializer<WorkoutLog> {

  private WorkoutDeserializer workoutDeserializer = new WorkoutDeserializer();

  /**
   * Deserializer to convert JOSN into workoutLog.
   *
   * @param parser used to parse JSON content
   * @param ctxt   provides configuration and control over deserialization process
   * @throws IOException      if there is a parsing error
   * @throws JacksonException if there is an error with Jackson processing
   */
  @Override
  public WorkoutLog deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    if (treeNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) treeNode;
      WorkoutLog workoutLog = new WorkoutLog();
      JsonNode workoutsNode = objectNode.get("workouts");
      if (workoutsNode instanceof ArrayNode) {
        for (JsonNode elementNode : ((ArrayNode) workoutsNode)) {
          Workout workout = workoutDeserializer.deserialize(elementNode);
          if (workout != null) {
            workoutLog.addWorkout(workout);
          }
        }
      }
      return workoutLog;
    }
    return null;
  }
}