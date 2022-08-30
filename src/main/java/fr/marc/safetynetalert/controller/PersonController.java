package fr.marc.safetynetalert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IPersonService;

@RestController
public class PersonController {

	@Autowired
    IPersonService personService;
	
	@Autowired
	JsonData jsonData;
 
	/**
    * @return - The entire person list in the database
    */
    @GetMapping("/persons")
    public List<Person> getPersons() {
    	
        return personService.getPersons(jsonData.getPersons());
    }
 
    /**
    * @return - a single person according to his firstName and lastName
    */   
    @GetMapping("/person")
    public Person getPersonByParam(@RequestParam String firstName, @RequestParam String lastName) {
     	
        return personService.getPerson(firstName, lastName, jsonData.getPersons());
    }
    
    @GetMapping("/communityEmail")
    public List<String> getEmailByCityData(@RequestParam String city) {
    	
		return personService.getEmailByCity(city, jsonData.getPersons());
	}
    
    
    @DeleteMapping("/person")
    public void deletePersonByParam(@RequestParam String firstName, @RequestParam String lastName) {
     	
        personService.deletePerson(firstName, lastName, jsonData.getPersons());
    }
    
    @PostMapping(value = "/person")
    public Person addPerson(@RequestBody Person person) {
    	return personService.savePerson(person, jsonData.getPersons());
    }
    
    @PutMapping(value = "/person")
    public Person updatePerson(@RequestParam String firstName, @RequestParam String lastName,@RequestBody Person person) {
    	return personService.updatePerson(firstName, lastName, person, jsonData.getPersons());
      
    }
    
    
    
}
