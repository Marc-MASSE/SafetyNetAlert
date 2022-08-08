package fr.marc.safetynetalert.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.service.impl.PersonServiceImpl;

@RestController
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

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
    @GetMapping("/person/{firstName}/{lastName}")
    public Optional<Person> getPerson(@PathVariable String firstName, @PathVariable String lastName) {
     	
        return personService.getPerson(firstName, lastName);
    }
    

}
