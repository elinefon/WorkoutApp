package ui;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    public void testHandleGetStarted() {
        clickOn("#getStartedLocally");
        verifyThat("#workoutsList", isVisible());
    }
}
