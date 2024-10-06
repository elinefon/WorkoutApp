package persistence.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import core.Workout;

public class WorkoutDeserializer extends JsonDeserializer<Workout> {

    @Override
    public Workout deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    public Workout deserialize(JsonNode jsonNode)  {
        if(jsonNode instanceof ObjectNode){
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode textNode = objectNode.get("description");
            if(textNode instanceof TextNode){
                Workout workout = new Workout(((TextNode) textNode).asText());
                return workout;
            }
        }
        return null;
    }

}