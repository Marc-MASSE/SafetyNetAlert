package fr.marc.safetynetalert.service;

import java.util.Optional;

import fr.marc.safetynetalert.model.Person;

public interface IPersonService {
	
	Optional<Person> getPerson(final String firstName, final String LastName);

	Iterable<Person> getPersons();

	void deletePerson(final String firstName, final String LastName);

	Person savePerson(Person person);


}
