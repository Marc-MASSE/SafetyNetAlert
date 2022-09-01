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
public class PersonInfoControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void getPersonInfoData_success() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=Eric&lastName=Cadigan"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstName", is("Eric")))
            .andExpect(jsonPath("$[0].lastName", is("Cadigan")))
            .andExpect(jsonPath("$[0].address", is("951 LoneTree Rd")))
            .andExpect(jsonPath("$[0].age", is(77)))
            .andExpect(jsonPath("$[0].email", is("gramps@email.com")))
            .andExpect(jsonPath("$[0].medications[0]", is("tradoxidine:400mg")))
            .andExpect(jsonPath("$[0].allergies[0]").doesNotExist());
    }

	@Test
    public void getPersonInfoData_no_answer() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=Nemo&lastName=Personne"))
            .andExpect(jsonPath("$[0]").doesNotExist());
    }
	
}
