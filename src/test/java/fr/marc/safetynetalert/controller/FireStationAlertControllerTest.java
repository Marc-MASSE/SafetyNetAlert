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
public class FireStationAlertControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void getFireStationAlertData_success() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("adultNumber", is(5)))
            .andExpect(jsonPath("childNumber", is(1)))
           	.andExpect(jsonPath("$.fireStationAlertPersonList[0].firstName", is("Peter")))
           	.andExpect(jsonPath("$.fireStationAlertPersonList[0].lastName", is("Duncan")))
           	.andExpect(jsonPath("$.fireStationAlertPersonList[0].address", is("644 Gershwin Cir")))
           	.andExpect(jsonPath("$.fireStationAlertPersonList[0].phone", is("841-874-6512")));
    }
	
	@Test
    public void getFireStationAlertData_no_answer() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=5"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]").doesNotExist());
	}

}
