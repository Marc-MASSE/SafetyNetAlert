package fr.marc.safetynetalert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.service.IPersonService;

@RestController
public class PersonController {

	@Autowired
    IPersonService personService;
 
	/**
    * Read - Get all persons
    * @return - An Iterable object of Person full filled
    */
    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
    	
        return personService.getPersons();
    }
 
    /**
    * Read - Get one person
    * @return - a single person according to his firstName and lastName
    */   
    @GetMapping("/person")
    public Person getPersonByParam(@RequestParam String firstName, @RequestParam String lastName) {
     	
        return personService.getPerson(firstName, lastName);
    }
    
    @DeleteMapping("/person")
    public void deletePersonByParam(@RequestParam String firstName, @RequestParam String lastName) {
     	
        personService.deletePerson(firstName, lastName);
    }
    
    
    
}
