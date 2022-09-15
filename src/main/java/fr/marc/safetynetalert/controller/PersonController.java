package fr.marc.safetynetalert.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.service.IPersonService;

/*
 * Controller used for endpoint /person
 * 
 * For updating person's data list
 */

@RestController
public class PersonController {
	
	static Logger log = LogManager.getLogger(PersonController.class.getName());
	
    private IPersonService personService;
    
    @Autowired
	public PersonController(IPersonService personService) {
		this.personService = personService;
	}
	
	/**
    * @return - The entire person list in the database
    */
    @GetMapping("/persons")
    public List<Person> getPersons() {
		log.info("GET request - endpoint /persons - return the entire list of persons");
        return personService.getPersons();
    }
 
    /**
    * @return - a single person according to his firstName and lastName
    */   
    @GetMapping("/person")
    public Person getPersonByParam(@RequestParam String firstName, @RequestParam String lastName) {
		log.info("GET request - endpoint /person - firstName = "+firstName+" lastName = "+lastName);
        return personService.getPerson(firstName, lastName);
    }
    
    @GetMapping("/communityEmail")
    public List<String> getEmailByCityData(@RequestParam String city) {
		log.info("GET request - endpoint /communityEmail - city = "+city);
		return personService.getEmailByCity(city);
	}
    
    @DeleteMapping("/person")
    public void deletePersonByParam(@RequestParam String firstName, @RequestParam String lastName) {
		log.info("DELETE request - endpoint /person - firstName = "+firstName+" lastName = "+lastName);
        personService.deletePerson(firstName, lastName);
    }
    
    @PostMapping(value = "/person")
    public Person addPerson(@RequestBody Person person) {
		log.info("POST request - endpoint /person - body = "+person);
    	return personService.savePerson(person);
    }
    
    @PutMapping(value = "/person")
    public Person updatePerson(@RequestParam String firstName, @RequestParam String lastName,@RequestBody Person person) {
		log.info("PUT request - endpoint /person - firstName = "+firstName+" lastName = "+lastName+" body = "+person);
    	return personService.updatePerson(firstName, lastName, person);
    }
}
