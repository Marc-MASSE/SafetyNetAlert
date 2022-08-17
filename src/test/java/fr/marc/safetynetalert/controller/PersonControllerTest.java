package fr.marc.safetynetalert.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.marc.safetynetalert.controller.PersonController;
import fr.marc.safetynetalert.service.impl.PersonServiceImpl;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonServiceImpl personService;

	@Test
	public void getPersonsTest() throws Exception {
		mockMvc.perform(get("/persons")).andExpect(status().isOk());
	}

}
