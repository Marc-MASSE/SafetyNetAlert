package fr.marc.safetynetalert.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.marc.safetynetalert.model.Person;

/*
 * Spring Data JPA interface to execute SQL queries, without needing to write them.
 * 
 * @Author Marc
 */

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
