package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import core.WorkoutLog;



public class RemoteAppControllerTest {

  private WireMockServer wireMockServer;

  
  @BeforeEach
  public void setup(){
    wireMockServer = new WireMockServer(8098); //Default to localserver
    wireMockServer.start();
    WireMock.configureFor("localhost", 8098);

  }

  @Test
  public void test(){
    assertTrue(true);

  }

  @AfterEach
  public void stopServer(){
    wireMockServer.stop();
  }
  
}
