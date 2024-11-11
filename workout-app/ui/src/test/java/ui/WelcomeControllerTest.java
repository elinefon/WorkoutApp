package ui;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit5.ApplicationTest;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static org.testfx.matcher.base.NodeMatchers.isVisible;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Tests the welcome controller for both remote and local application
 * Use wiremock to see if the remote application loads as it should
 */
public class WelcomeControllerTest extends ApplicationTest {

    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Welcome.fxml"));
        root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void testHandleGetStartedLocally() {
        clickOn("#getStartedLocally");
        verifyThat("#workoutsList", isVisible());
    }

    @Test
    public void testHandleGetStartedRemotelyWithNoServer() {
        clickOn("#getStartedRemote");
        verifyThat("#ErrorAlert", isVisible());
        
    }

    @Test
    public void testHandleGetStartedRemotelyWireMockServer() {
        WireMockServer wireMockServer = new WireMockServer("8080");
        wireMockServer.start();
        WireMock.configureFor("localhost", 8080);
        clickOn("#getStartedRemote");
        verifyThat("#workoutsList", isVisible());
        wireMockServer.stop();
    }
}
