package fr.marc.safetynetalert.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.net.ObjectWriter;
import fr.marc.safetynetalert.constants.DBConstants;
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
	    public void getPersons() throws Exception {
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
		
		@Test
	    public void no_answer() throws Exception {
	        mockMvc.perform(get("/person?firstName=Nemo&lastName=Personne"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0]").doesNotExist());
	    }
	}


	@Nested
	class GetEmailByCity {
		@Test
	    public void success() throws Exception {
	        mockMvc.perform(get("/communityEmail?city=Culver"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0]", is("jaboyd@email.com")));
	    }
		
		@Test
	    public void no_answer() throws Exception {
	        mockMvc.perform(get("/communityEmail?city=Nowhere"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0]").doesNotExist());
	    }
	}
	
	@Nested
	class DeletePerson {
		@Test
	    public void success() throws Exception {
	        mockMvc.perform(delete("/person?firstName=Eric&lastName=Cadigan"))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/person?firstName=Eric&lastName=Cadigan"))
            	.andExpect(status().isOk())
            	.andExpect(jsonPath("$[0]").doesNotExist());
	    }
		
		@Test
	    public void no_body() throws Exception {
	        mockMvc.perform(delete("/person?firstName=Nemo&lastName=Personne"))
	            .andExpect(status().isOk());
		}
	}
	
	@Nested
	class AddPerson {
		@Test
	    public void success() throws Exception {
			
			ObjectMapper mapper = new ObjectMapper();  
			
	        mockMvc.perform(post("/person")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(mapper.writeValueAsString(DBConstants.personToAdd)))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/person?firstName=Adeline&lastName=Plus"))
            	.andExpect(status().isOk())
	            .andExpect(jsonPath("firstName", is("Adeline")))
	            .andExpect(jsonPath("lastName", is("Plus")));
	    }
		
	}
}


