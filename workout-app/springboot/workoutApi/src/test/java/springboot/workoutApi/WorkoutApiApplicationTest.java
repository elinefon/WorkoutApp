package springboot.workoutApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import core.Workout;
import core.WorkoutLog;

/**
 * This application test tests the service and controller.
 * This is done by creating an mock request
 */
@WebMvcTest(WorkoutLogController.class)
//@ContextConfiguration(classes = {WorkoutLogService.class})
@AutoConfigureMockMvc
public class WorkoutApiApplicationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WorkoutLogService service;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @SpyBean
  private WorkoutLogController controller;

  WorkoutLog workoutLog;

  @BeforeEach
  void setup(){
    this.controller = new WorkoutLogController(service);
    this.workoutLog = new WorkoutLog();
    Workout w1 = new Workout("Arms");
    Workout w2 = new Workout("Legs");
    workoutLog.addWorkout(w2);
    workoutLog.addWorkout(w1);
  }

  @Test
  void assertNotNull() throws Exception{
    Assertions.assertNotNull(mockMvc);
  }

   @Test
    void testGetWorkoutLog() throws Exception{

      when(service.getWorkouts()).thenReturn(workoutLog.getWorkouts());

      MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/")
        .header("Content-Type", "application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

        assertEquals("[{\"workoutInput\":\"Legs\",\"date\":\"2024-11-14\"},{\"workoutInput\":\"Arms\",\"date\":\"2024-11-14\"}]", result.getResponse().getContentAsString());
        assertEquals(200, result.getResponse().getStatus());
        
        System.out.println(result.getResponse().getContentAsString());
        System.out.println(result.getResponse().getStatus());
    }

  @Test
  void testGetWorkout() throws Exception{

    when(service.getWorkout("Arms")).thenReturn(Optional.of(workoutLog.getWorkout("Arms")));

    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/workout?workoutInput=Arms")
      .header("Content-Type", "application/json"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andReturn();

    assertEquals("{\"workoutInput\":\"Arms\",\"date\":\"2024-11-14\"}", result.getResponse().getContentAsString());
    assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    void testAddWorkout() throws Exception{
  
      when(service.addWorkout("Arms", null)).thenReturn(workoutLog.getWorkout("Arms"));
  
      ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.post("/workout?workoutInput=Arms")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"workoutInput\":\"Arms\",\"date\":\"2024-11-14\"}"));
  
      System.out.println(result.andReturn().getResponse().getContentAsString());
    }

}
