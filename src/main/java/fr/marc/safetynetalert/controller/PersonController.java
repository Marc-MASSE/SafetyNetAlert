package fr.marc.safetynetalert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.service.PersonService;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
    * Read - Get all employees
    * @return - An Iterable object of Person full filled
    */
    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return personService.getPersons();
    }

}
