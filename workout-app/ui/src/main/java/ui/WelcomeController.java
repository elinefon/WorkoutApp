package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The controller for the first window of the application desciding what application 
 * to use (remote or local storage).
 */
public class WelcomeController {

  /**
   * This launches the application, setting the controller to the correct version.
   *
   * @param controller controller that will handle logic
   * @param event event that triggered this action, used to retrieve primary stage 
   * @throws IOException if error occurs while loading file
   */
  private void launchApplication(AppController controller, ActionEvent event) throws IOException {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
    loader.setController(controller);

    //Loads content of App.fxml into parent node to initialize the interface of App.fxml
    Parent appRoot = loader.load();

    //Retrieves the main window of the app
    Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

    //Sets scene of primary scene created using approot (which loads content of App.fxml)
    primaryStage.setScene(new Scene(appRoot));
  }    
  
  /**
   * Creates a local controller and calls the launch application.
   *
   * @param event event that triggered this action
   * @throws IOException if error occurs while loading file
   */

  
  @FXML
  public void handleStartLocally(ActionEvent event) throws IOException {
    AppController controller = new LocalAppController();
    launchApplication(controller, event);  
  }

  /**
   * Creates a remote controller and calls the launch application.
   *
   * @param event event that triggered this action
   * @throws IOException if error occurs while loading file
   */
  @FXML
  public void handleStartRemote(ActionEvent event) throws IOException {  
    AppController controller = new RemoteAppController();
    launchApplication(controller, event);
  }
}
