package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The controller for the first window of the application desciding what application 
 * to use (remote or local storage).
 */
public class WelcomeController {

  /**
   * This launches the application, setting the controller to the correct version.
   *
   * @param controller controller that manages application logic
   * @param event the event that triggered the method
   * @throws IOException if the FXML file cannot be loaded
   */
  private void launchApplication(AppController controller, ActionEvent event) throws IOException {
    //Creates new fxml-loader instance with the main page with the log
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
   * @param event the event that triggered this method
   * @throws IOException if loading the FXML file fails
   */
  @FXML
  public void handleStartLocally(ActionEvent event) throws IOException {
    AppController controller = new LocalAppController();
    launchApplication(controller, event);  
  }

    /**
     * Creates a remote controller and calls the launch application
     * @param event
     * if there is an issue reaching the server an alert box will show
     */
    @FXML
    public void handleStartRemote(ActionEvent event) {  
        try{
            AppController controller = new RemoteAppController();
            launchApplication(controller, event);
        } catch (Exception e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.getDialogPane().setId("ErrorAlert");
            alert.setContentText("Check that springboot is running in another terminal.");
            alert.showAndWait();
        }
        
    
    }
  }
}
