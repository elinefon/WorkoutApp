package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import com.github.tomakehurst.wiremock.WireMockServer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
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
    wireMockServer = new WireMockServer(8089); //Default to localserver
    wireMockServer.start();

    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("App.fxml"));
    controller = new RemoteAppController("8089");
    fxmlLoader.setController(controller);
    root = fxmlLoader.load();
    stage.setScene(new Scene(root));
    stage.show();
    robot = new FxRobot();
  }
  
  /**
   * Make the robot type a string. 
   * This method have support for space and exclamation mark
   * @param string to type
   */
  private void typeString(String string){ 
    String keys[] = string.split("");
    for (String i : keys){
      KeyCode key;
      if (i.equals(" ")){
        key = KeyCode.SPACE;
      }else if (i.equals("!")){
        robot.press(KeyCode.SHIFT, KeyCode.DIGIT1);
        robot.release(KeyCode.SHIFT, KeyCode.DIGIT1);
        return;
      }else{
        key = KeyCode.getKeyCode(i.toUpperCase());
      }

      robot.press(key);
      robot.release(key);
    }
  }

  private void registerWorkout(String s){
    clickOn("#inputWorkout" );
    typeString(s);
    clickOn("#registerButton");
  }

  @Test
  public void testSpace(){
    registerWorkout("Arms are cool");
    Label errorLabel = (Label) lookup("#errorLabel").query();
    assertTrue(errorLabel.getText() == "");
  }

  @Test
  public void testIllegalChars(){
    registerWorkout("Arms are cool!");
    Label errorLabel = (Label) lookup("#errorLabel").query();
    assertEquals("There can be no special characters", errorLabel.getText());
  }

  @AfterEach
  public void stopServer(){
    wireMockServer.stop();
  }
  
}
