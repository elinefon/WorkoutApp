package springboot.workoutApi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * This application test tests the service and controller.
 * This is done by creating an mock request
 */
@WebMvcTest(WorkoutLogController.class)
public class WorkoutApiApplicationTest {

  @Autowired
  private MockMvc mockmvc;

  @MockBean
  private WorkoutLogService service;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Test
  void assertNotNull() throws Exception{
    Assertions.assertNotNull(mockmvc);
  }
}
