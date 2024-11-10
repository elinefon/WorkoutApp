package ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class RemoteAccessTest {
   private WireMockServer wireMockServer;

  
  @BeforeEach
  public void setup(){
    wireMockServer = new WireMockServer(8098); //Default to localserver
    wireMockServer.start();
    WireMock.configureFor("localhost", 8098);

  }

  @Test
  public void testGetWorkoutLog(){
    assertTrue(true);

  }

  @Test
  public void testGetWorkout(){
    assertTrue(true);

  }

  @Test
  public void testAddWorkout(){
    assertTrue(true);

  }

  @Test
  public void testRemoveWorkoutLog(){
    assertTrue(true);

  }

  @AfterEach
  public void stopServer(){
    wireMockServer.stop();
  }
}
