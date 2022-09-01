package fr.marc.safetynetalert.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneAlertControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void getPhoneAlertData_success() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]", is("841-874-6512")));
    }
	
	@Test
    public void getPhoneAlertData_no_answer() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=5"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]").doesNotExist());
    }

}
