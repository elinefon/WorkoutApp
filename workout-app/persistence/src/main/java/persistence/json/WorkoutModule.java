package persistence.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.Workout;
import core.WorkoutLog;


public class WorkoutModule extends SimpleModule {
  private static final String NAME = "WorkoutModule";
//  private static final VersionUtil VERSION_UTIL = new VersionUtil() {};

  @SuppressWarnings("deprecation")
    public WorkoutModule() {
        //super(NAME, VERSION_UTIL.version());
        super(NAME, Version.unknownVersion());

        addSerializer(Workout.class, new WorkoutSerializer());
        addSerializer(WorkoutLog.class, new WorkoutLogSerializer());
        addDeserializer(Workout.class, new WorkoutDeserializer());
        addDeserializer(WorkoutLog.class, new WorkoutLogDeserializer());
    }

    public static void main(String args[]){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new WorkoutModule());
        Workout w = new Workout("Legs");
        Workout w2 = new Workout("Arms");
        WorkoutLog wl = new WorkoutLog();
        wl.addWorkout(w);
        wl.addWorkout(w2);
        try {
            String json = mapper.writeValueAsString(wl);
            System.out.println(json);
            WorkoutLog log = mapper.readValue(json, WorkoutLog.class);
            System.out.println(log);
        } catch (JsonProcessingException e) {
            System.err.println("WÆÆÆÆÆÆ!!");
        }
    }

}

