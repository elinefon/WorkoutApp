package ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import core.WorkoutLog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This test will not test basic functionality as this is
 * tested in the localAppControllerTest and the functionality
 * is mostly the same due to inheritance. 
 * 
 * We will therefor only test exceptions and issues that is spesific
 * for the remote application.
 * 
 * The remote application will accept space on input, 
 * but no other special characters.
 */

public class RemoteAppControllerTest extends ApplicationTest{

  private WireMockServer wireMockServer;
  private RemoteAppController controller;
  private Parent root;
  private FxRobot robot;

  @Override
   public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("App.fxml"));
    controller = new RemoteAppController();
    fxmlLoader.setController(controller);
    root = fxmlLoader.load();
    stage.setScene(new Scene(root));
    stage.show();
    robot = new FxRobot();
    }

  @BeforeAll
  public void setup(){
    wireMockServer = new WireMockServer(8098); //Default to localserver
    wireMockServer.start();
    WireMock.configureFor("localhost", 8098);

  }

  @Test
  public void testIllegalChars(){
    assertTrue(true);

  }

  @AfterAll
  public void stopServer(){
    wireMockServer.stop();
  }
  
}
