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
public class ChildAlertControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void GetChildAlertData() throws Exception {
        mockMvc.perform(get("/childAlert?address=1509 Culver St"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.child[0].firstName", is("Tenley")))
            .andExpect(jsonPath("$.child[0].lastName", is("Boyd")))
            .andExpect(jsonPath("$.child[0].age", is(10)))
            .andExpect(jsonPath("$.otherMember[0].firstName", is("John")))
            .andExpect(jsonPath("$.otherMember[0].lastName", is("Boyd")))
            .andExpect(jsonPath("$.otherMember[0].age", is(38)));
    }

}
