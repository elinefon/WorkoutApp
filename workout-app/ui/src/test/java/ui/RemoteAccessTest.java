package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import core.Workout;
import core.WorkoutLog;

public class RemoteAccessTest {
   private WireMockServer wireMockServer;
   private RemoteAccess access;
   private WireMockConfiguration config;

  
  @BeforeEach
  public void setup() throws URISyntaxException{
    config = WireMockConfiguration.wireMockConfig().port(8089);
    wireMockServer = new WireMockServer(config.portNumber()); //Default to localserver
    wireMockServer.start();
    WireMock.configureFor("http", "localhost", 8089);
    access = new RemoteAccess("8089");
  }

  @Test
  public void testGetWorkoutLog(){
    stubFor(get(urlEqualTo("/"))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody("[{\"workoutInput\": \"Arms\", \"date\": \"2024-11-01\"}]")));
    
    WorkoutLog wlog = access.getWorkoutLog();

    assertEquals(1, wlog.getWorkouts().size());
    for(Workout w: wlog.getWorkouts()){
      assertEquals("Arms", w.getWorkoutInput());
    }
  }

  @Test
  public void testGetWorkout(){
    stubFor(get(urlEqualTo("/workout?workoutInput=Arms"))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody("{\"workoutInput\": \"Arms\", \"date\": \"2024-11-01\"}")));

    Workout workout = access.getWorkout("Arms", null);
    assertEquals("Arms", workout.getWorkoutInput());
    assertEquals(LocalDate.of(2024, 11, 01), workout.getDate());
  }

  @Test
  public void testAddWorkout(){
    stubFor(post(urlEqualTo("/workout?workoutInput=Work&date=2024-11-01"))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody("{\"workoutInput\": \"Work\", \"date\": \"2024-11-01\"}")));

    Workout newWorkout = new Workout("Work", LocalDate.of(2024, 11, 01));
    Workout workout = access.addWorkout(newWorkout);
    assertEquals(newWorkout.getWorkoutInput(), workout.getWorkoutInput());
  }

  @Test
  public void testRemoveWorkoutLog(){
    stubFor(delete(urlEqualTo("/workout?workoutInput=IncorrectWork&date=2024-11-01"))
    .willReturn(aResponse()
    .withHeader("Content-Type", "application/json")
    .withBody("{\"workoutInput\": \"IncorrectWork\", \"date\": \"2024-11-01\"}")));

    Workout incorrectWorkout = new Workout("IncorrectWork", LocalDate.of(2024, 11, 01));
    access.removeWorkout(incorrectWorkout);
  }

  @AfterEach
  public void stopServer(){
    wireMockServer.stop();
  }
}
