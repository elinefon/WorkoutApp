package persistence.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.Workout;
import core.WorkoutLog;


public class WorkoutModule extends SimpleModule {
  private static final String NAME = "WorkoutModule";
  private static final VersionUtil VERSION_UTIL = new VersionUtil() {};

  @SuppressWarnings("deprecation")
    public WorkoutModule() {
        super(NAME, VERSION_UTIL.version());
        addSerializer(Workout.class, new WorkoutSerializer());
        addSerializer(WorkoutLog.class, new WorkoutLogSerializer());
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
            System.out.println(mapper.writeValueAsString(wl));
        } catch (JsonProcessingException e) {
            System.err.println("WÆÆÆÆÆÆ!!");
        }
    }

}

