package fr.marc.safetynetalert.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import fr.marc.safetynetalert.service.impl.JsonDataService;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	JsonDataService jsonDataService;
	
	@AfterEach
	public void cleanUpTheDataBase() throws IOException {
		jsonDataService.initialization();
	}
	

	@Test
	    public void GetPersons() throws Exception {
	        mockMvc.perform(get("/persons"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].firstName", is("John")))
	            .andExpect(jsonPath("$[0].lastName", is("Boyd")));
	    }
	
	@Nested
	class GetPerson {
		@Test
	    public void success() throws Exception {
	        mockMvc.perform(get("/person?firstName=Eric&lastName=Cadigan"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("firstName", is("Eric")))
	            .andExpect(jsonPath("lastName", is("Cadigan")));
	    }
		
		
	}


	@Test
    public void GetEmailByCity() throws Exception {
        mockMvc.perform(get("/communityEmail?city=Culver"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]", is("jaboyd@email.com")));
    }
	
	@Test
    public void DeletePerson() throws Exception {
        mockMvc.perform(delete("/person?firstName=Eric&lastName=Cadigan"))
            .andExpect(status().isOk());
    }
	
	
}


