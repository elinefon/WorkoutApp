package persistence.json;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import core.Workout;

/**
 * Deserializer to convert JSON data into a single Workout object.
 */
public class WorkoutDeserializer extends JsonDeserializer<Workout> {

    /**
     * Deserializes JSON into Workout object
     * @param parser used to parse JSON content
     * @param ctxt provides configuration and control over deserialization process
     * @throws IOException if there is a parsing error
     * @throws JacksonException if there is an error with Jackson processing
     */
    @Override
    public Workout deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    /**
     * Helper method to handle JSON node deserialization.
     * Reads description and date fields to create Workout instance
     * @param jsonNode node containing data to be deserialized
     * @return Workout object if parsing is successful, null otherwise
     */
    public Workout deserialize(JsonNode jsonNode)  {
        if(jsonNode instanceof ObjectNode){
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode textNode = objectNode.get("description");
            JsonNode dateNode = objectNode.get("date");
            if(textNode instanceof TextNode && dateNode instanceof TextNode){
                String dateString = ((TextNode) dateNode).asText();
                LocalDate date = LocalDate.parse(dateString);
                Workout workout = new Workout(((TextNode) textNode).asText(), date);
                return workout;
            }
        }
        return null;
    }
}