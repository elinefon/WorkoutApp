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
 * to use (remote or local storage)
 */
public class WelcomeController {

    /**
     * This launches the application, setting the controller to the correct version
     * @param controller
     * @param event
     * @throws IOException
     */
    private void launchApplication(AppController controller, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml")); //creates new fxml-loader instance with the main page with the log
        loader.setController(controller);
        Parent appRoot = loader.load(); //loads content of App.fxml into parent node to initialize the interface of App.fxml
        Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow(); //retrieves the main window of the app
        primaryStage.setScene(new Scene(appRoot)); //sets scene of primary scene created using approot (which loads content of App.fxml)
    }    
    
    /**
     * Creates a local controller and calls the launch application
     * @param event
     * @throws IOException
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
            alert.setContentText("Check that springboot is running in another terminal.");
            alert.showAndWait();
        }
        
    
    }
}
