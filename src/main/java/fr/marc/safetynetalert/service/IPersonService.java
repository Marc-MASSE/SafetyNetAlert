package fr.marc.safetynetalert.service;

import fr.marc.safetynetalert.model.Person;

public interface IPersonService {
	
	Person getPerson(final String firstName, final String lastName);

	Iterable<Person> getPersons();

	void deletePerson(final String firstName, final String lastName);

	Person savePerson(Person person);


}
