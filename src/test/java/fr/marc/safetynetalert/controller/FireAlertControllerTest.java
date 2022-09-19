package fr.marc.safetynetalert.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FireAlertControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void getFireAlertData_success() throws Exception {
        mockMvc.perform(get("/fire?address=1509 Culver St"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstName", is("John")))
            .andExpect(jsonPath("$[0].lastName", is("Boyd")))
            .andExpect(jsonPath("$[0].phone", is("841-874-6512")))
            .andExpect(jsonPath("$[0].medications", is(List.of("aznol:350mg", "hydrapermazol:100mg"))))
            .andExpect(jsonPath("$[0].allergies", is(List.of("nillacilan"))));
    }

	@Test
    public void getFireAlertData_no_answer() throws Exception {
        mockMvc.perform(get("/fire?address=Nowhere"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]").doesNotExist());
    }
}
