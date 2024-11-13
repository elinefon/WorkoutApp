package springboot.workoutApi.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import persistence.WorkoutPersistence;
import core.WorkoutLog;
import core.Workout;
import springboot.workoutApi.service.WorkoutLogService;
import springboot.workoutApi.service.WorkoutLogConfig;
import springboot.workoutApi.WorkoutApiApplication;
import org.springframework.context.annotation.Import;

/*
 * @AutoConfigureMockMvc
 * @WebMvcTest
 * @ContextConfiguration(classes = { WorkoutApiApplication.class, WorkoutLogService.class, WorkoutLogController.class })
 * 
 * @AutoConfigureMockMvc
@SpringBootTest(classes = WorkoutApiApplication.class)
 */


@WebMvcTest(WorkoutLog.class)
public class WorkoutApiApplicationTest {
    
    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private WorkoutLogService workoutLogService;


	@Test
    void testCreateMockMvc(){
        assertNotNull(mockMvc);
    }
  
}

 /*

    private WorkoutPersistence persistence;
 
 
 
 
 
 
 @MockBean
    private WorkoutLogService workoutLogService;

    @MockBean
    private WorkoutLog workoutLog;
    */

    /* 
    @Test
    void testGetWorkoutLog() throws Exception {
        WorkoutLog wlog = new WorkoutLog();
        wlog.addWorkout(new Workout("Workout"));

        when(workoutLogService.getWorkouts()).thenReturn(wlog.getWorkouts());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/")
        .header("Content-Type", "application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddWorkout() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/workout")
            .)
    }
    */

