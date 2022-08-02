package fr.marc.safetynetalert.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@GetMapping("/person")
	public String personsList() {
		return "Une personne";
	}
	
	@GetMapping("/person/{id}")
	public String displayAPerson(@PathVariable String lastName) {
	  return "Vous avez demandé une personne avec le nom de famille " + lastName;
	}

}
