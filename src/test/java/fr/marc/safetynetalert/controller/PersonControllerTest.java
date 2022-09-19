package fr.marc.safetynetalert.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.service.impl.JsonDataService;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	JsonDataService jsonDataService;
	
	private Person personToAdd = new Person("Adeline","Plus","1 rue de Chanteloup","Limoges","87000","12-34-56-78-00","adplus@email.com");
	
	@BeforeEach
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
	        		.content(mapper.writeValueAsString(personToAdd)))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/person?firstName=Adeline&lastName=Plus"))
            	.andExpect(status().isOk())
	            .andExpect(jsonPath("firstName", is("Adeline")))
	            .andExpect(jsonPath("lastName", is("Plus")));
	    }
	}
	
	@Nested
	class UpdatePerson {
		@Test
	    public void success() throws Exception {
			
			ObjectMapper mapper = new ObjectMapper();  
			
	        mockMvc.perform(put("/person?firstName=Eric&lastName=Cadigan")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(mapper.writeValueAsString(personToAdd)))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/person?firstName=Eric&lastName=Cadigan"))
            	.andExpect(status().isOk())
	            .andExpect(jsonPath("firstName", is("Eric")))
	            .andExpect(jsonPath("lastName", is("Cadigan")))
	            .andExpect(jsonPath("address", is("1 rue de Chanteloup")))
	            .andExpect(jsonPath("city", is("Limoges")))
	            .andExpect(jsonPath("zip", is("87000")))
	            .andExpect(jsonPath("phone", is("12-34-56-78-00")))
	            .andExpect(jsonPath("email", is("adplus@email.com")));
	    }
		
		@Test
	    public void no_body() throws Exception {
			
			ObjectMapper mapper = new ObjectMapper();  
			
	        mockMvc.perform(put("/person?firstName=Nemo&lastName=Personne")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(mapper.writeValueAsString(personToAdd)))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/person?firstName=Nemo&lastName=Personne"))
            	.andExpect(status().isOk())
	            .andExpect(jsonPath("$[0]").doesNotExist());
	    }
	}
	
}


