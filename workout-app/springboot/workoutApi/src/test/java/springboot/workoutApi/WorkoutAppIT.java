package springboot.workoutApi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import ui.RemoteAppController;
import ui.WelcomeController;
import ui.App;

public class WorkoutAppIT extends ApplicationTest {

    private WelcomeController controller;

    @BeforeAll
    public static void setupHeadless() {
        App.supportHeadless();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/ui/Welcome.fxml"));
        Parent parent = fxmlLoader.load();
        this.controller = fxmlLoader.getController();
        stage.setScene(new Scene(parent));
        stage.setTitle("Workout Log");
        stage.show();
    }

    @BeforeEach
    public void setupItems() throws URISyntaxException {
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/it-workoutlog.json"))) {
            String port = System.getProperty("workout.port");
            assertNotNull(port, "workout.port system property was not set");
            URI baseUri = new URI("http://localhost:" + port + "/workout");
            System.out.println("Base RemoteAccess URI: " + baseUri);
        } catch (IOException ioe) {
            fail(ioe.getMessage());
        }
    }

    @Test
    public void testController_initial() {
        assertNotNull(this.controller);
    }

}