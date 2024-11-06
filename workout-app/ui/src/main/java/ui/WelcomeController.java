package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeController {
    
    @FXML
    public void handleStartLocally(ActionEvent event) throws IOException {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("App.fxml")); //creates new fxml-loader instance with the main page with the log
            Parent appRoot = loader.load(); //loads content of App.fxml into parent node to initialize the interface of App.fxml

            Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow(); //retrieves the main window of the app

            primaryStage.setScene(new Scene(appRoot)); //sets scene of primary scene created using approot (which loads content of App.fxml)
    }

    @FXML
    public void handleStartRemote(ActionEvent event) throws IOException {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("RemoteApp.fxml")); //creates new fxml-loader instance with the main page with the log
            Parent appRoot = loader.load(); //loads content of RemoteApp.fxml into parent node to initialize the interface of RemoteApp.fxml

            Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow(); //retrieves the main window of the app

            primaryStage.setScene(new Scene(appRoot)); //sets scene of primary scene created using approot (which loads content of App.fxml)
    }
}
