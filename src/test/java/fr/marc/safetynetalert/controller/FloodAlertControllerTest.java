package fr.marc.safetynetalert.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FloodAlertControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void getFloodAlertData_success() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=1,2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].stationNumber", is("1")))
            .andExpect(jsonPath("$[0].floodAlertPerStation[0].firstName", is("Peter")))
            .andExpect(jsonPath("$[0].floodAlertPerStation[0].lastName", is("Duncan")))
            .andExpect(jsonPath("$[1].stationNumber", is("2")))
            .andExpect(jsonPath("$[1].floodAlertPerStation[0].firstName", is("Jonanathan")))
            .andExpect(jsonPath("$[1].floodAlertPerStation[0].lastName", is("Marrack")));
    }

	@Test
    public void getFloodAlertData_no_answer() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=5"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].stationNumber", is("5")))
            .andExpect(jsonPath("$[0].floodAlertPerStation[0]").doesNotExist());
    }
	
}
