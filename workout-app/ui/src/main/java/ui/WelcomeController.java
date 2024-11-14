package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The controller for the first window of the application desciding what application 
 * to use (remote or local storage).
 */
public class WelcomeController {

  @FXML
  private TextField ipInput;

  /**
   * This launches the application, setting the controller to the correct version.
   *
   * @param controller the controller deciding the application
   * @param event the event that triggered the action
   * @throws IOException if there is an issue with launching
   */
  private void launchApplication(AppController controller, ActionEvent event) throws IOException {
    //creates new fxml-loader instance with the main page with the log
    FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
    loader.setController(controller);
    //loads content of App.fxml into parent node to initialize the interface of App.fxml
    Parent appRoot = loader.load();
    //retrieves the main window of the app
    Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
    //sets scene of primary scene created using approot (which loads content of App.fxml)
    primaryStage.setScene(new Scene(appRoot));
  }
  
  /**
   * Creates a local controller and calls the launch application.
   *
   * @param event the event that triggered the event
   * @throws IOException if there is an issue with starting
   */
  @FXML
  public void handleStartLocally(ActionEvent event) throws IOException {
    AppController controller = new LocalAppController();
    launchApplication(controller, event);  
  }

  /**
   * Creates a remote controller and calls the launch application.
   * Ff there is an issue reaching the server an alert box will show.
   *
   * @param event the event that triggered the action
   */
  @FXML
  public void handleStartRemote(ActionEvent event) {  
    String ip = ipInput.getText();
    try {
      AppController controller = new RemoteAppController(ip);
      launchApplication(controller, event);
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.getDialogPane().setId("ErrorAlert");
      alert.setContentText("Check that springboot is running in another terminal.");
      alert.showAndWait();
    }
  }
}
