package persistence.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.Workout;
import core.WorkoutLog;


public class WorkoutModule extends SimpleModule {
  private static final String NAME = "WorkoutModule";

  public WorkoutModule() {
        //super(NAME, VERSION_UTIL.version());
        super(NAME, Version.unknownVersion());

        //adding all serializers and deserializer
        addSerializer(Workout.class, new WorkoutSerializer());
        addSerializer(WorkoutLog.class, new WorkoutLogSerializer());
        addDeserializer(Workout.class, new WorkoutDeserializer());
        addDeserializer(WorkoutLog.class, new WorkoutLogDeserializer());
    }

}

