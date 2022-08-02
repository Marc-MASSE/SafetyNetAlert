package fr.marc.safetynetalert.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.marc.safetynetalert.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
