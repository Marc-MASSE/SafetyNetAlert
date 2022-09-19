package fr.marc.safetynetalert.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
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

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.service.impl.JsonDataService;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	JsonDataService jsonDataService;
	
	private FireStation fireStationToAdd = new FireStation("1 rue de Chanteloup","4");
	
	@BeforeEach
	public void cleanUpTheDataBase() throws IOException {
		jsonDataService.initialization();
	}
	

	@Test
	    public void getPersons() throws Exception {
	        mockMvc.perform(get("/firestations"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
	            .andExpect(jsonPath("$[0].station", is("3")));
	    }
	
	@Nested
	class DeleteFireStation {
		@Test
	    public void by_address_success() throws Exception {
	        mockMvc.perform(delete("/firestation?address=1509 Culver St&&stationNumber="))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/firestations"))
            	.andExpect(status().isOk())
            	.andExpect(jsonPath("$[0].address", is("29 15th St")));
	    }
		
		@Test
	    public void nothing() throws Exception {
	        mockMvc.perform(delete("/firestation?address=&&stationNumber="))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/firestations"))
            	.andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
	            .andExpect(jsonPath("$[0].station", is("3")));
	    }
	}
	
	@Nested
	class AddFireStation {
		@Test
		   public void success() throws Exception {
				
			ObjectMapper mapper = new ObjectMapper();  
				
		    mockMvc.perform(post("/firestation")
		        	.contentType(MediaType.APPLICATION_JSON)
		        	.content(mapper.writeValueAsString(fireStationToAdd)))
		        .andExpect(status().isOk());
		    mockMvc.perform(get("/firestations"))
	            .andExpect(status().isOk())
		        .andExpect(jsonPath("$[13].address", is("1 rue de Chanteloup")))
		        .andExpect(jsonPath("$[13].station", is("4")));
		    
		    }
	}
	
	@Nested
	class UpdateFireStation {
		@Test
	    public void success() throws Exception {
			
	        mockMvc.perform(put("/firestation?address=1509 Culver St")
	        		.contentType(anyString())
	        		.content("5"))
	            .andExpect(status().isOk());
	        mockMvc.perform(get("/firestations"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$[0].address", is("1509 Culver St")))
        		.andExpect(jsonPath("$[0].station", is("5")));
	    }
		
		@Test
	    public void nowhere() throws Exception {
			
		       mockMvc.perform(put("/firestation?address=nowhere")
		        		.contentType(anyString())
		        		.content("5"))
		            .andExpect(status().isOk());
		    }
	}
	
}
