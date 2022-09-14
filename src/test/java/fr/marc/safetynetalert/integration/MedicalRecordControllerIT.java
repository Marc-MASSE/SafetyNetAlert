package fr.marc.safetynetalert.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.service.impl.JsonDataService;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	JsonDataService jsonDataService;
	
	@BeforeEach
	public void cleanUpTheDataBase() throws IOException {
		jsonDataService.initialization();
	}
	

	@Test
	    public void getMedicalRecords() throws Exception {
	        mockMvc.perform(get("/medicalRecords"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].firstName", is("John")))
	            .andExpect(jsonPath("$[0].lastName", is("Boyd")))
	            .andExpect(jsonPath("$[0].birthdate", is("03/06/1984")))
	            .andExpect(jsonPath("$[0].medications", is(List.of("aznol:350mg", "hydrapermazol:100mg"))))
	            .andExpect(jsonPath("$[0].allergies", is(List.of("nillacilan"))));
	    }

	@Nested
	class GetMedicalRecord {
		@Test
	    public void success() throws Exception {
	        mockMvc.perform(get("/medicalRecord?firstName=Eric&lastName=Cadigan"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("firstName", is("Eric")))
	            .andExpect(jsonPath("lastName", is("Cadigan")));
	    }
		
		@Test
	    public void no_answer() throws Exception {
	        mockMvc.perform(get("/medicalRecord?firstName=Nemo&lastName=Personne"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0]").doesNotExist());
	    }
	}

	@Nested
	class DeleteMedicalRecord {
		@Test
	    public void success() throws Exception {
	        mockMvc.perform(delete("/medicalRecord?firstName=Eric&lastName=Cadigan"))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/medicalRecord?firstName=Eric&lastName=Cadigan"))
            	.andExpect(status().isOk())
            	.andExpect(jsonPath("$[0]").doesNotExist());
	    }
		
		@Test
	    public void no_body() throws Exception {
	        mockMvc.perform(delete("/medicalRecord?firstName=Nemo&lastName=Personne"))
	            .andExpect(status().isOk());
		}
	}
	
	@Nested
	class AddMedicalRecord {
		@Test
	    public void success() throws Exception {
			
			ObjectMapper mapper = new ObjectMapper();  
			
	        mockMvc.perform(post("/medicalRecord")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(mapper.writeValueAsString(DBConstants.medicalRecordToAdd)))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/medicalRecord?firstName=Adeline&lastName=Plus"))
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
			
	        mockMvc.perform(put("/medicalRecord?firstName=Eric&lastName=Cadigan")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(mapper.writeValueAsString(DBConstants.medicalRecordToAdd)))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/medicalRecord?firstName=Eric&lastName=Cadigan"))
            	.andExpect(status().isOk())
	            .andExpect(jsonPath("firstName", is("Eric")))
	            .andExpect(jsonPath("lastName", is("Cadigan")))
	            .andExpect(jsonPath("birthdate", is("05/17/1983")))
	            .andExpect(jsonPath("medications", is(List.of("Camomille"))))
	            .andExpect(jsonPath("allergies", is(List.of("Tilleul"))));
	    }
		
		@Test
	    public void no_body() throws Exception {
			
			ObjectMapper mapper = new ObjectMapper();  
			
	        mockMvc.perform(put("/medicalRecord?firstName=Nemo&lastName=Personne")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(mapper.writeValueAsString(DBConstants.medicalRecordToAdd)))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/medicalRecord?firstName=Nemo&lastName=Personne"))
            	.andExpect(status().isOk())
	            .andExpect(jsonPath("$[0]").doesNotExist());
	    }
	}
	

}
