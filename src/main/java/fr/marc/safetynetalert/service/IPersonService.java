package fr.marc.safetynetalert.service;

import fr.marc.safetynetalert.model.Person;

public interface IPersonService {
	
	Person getPerson(final String firstName, final String LastName);

	Iterable<Person> getPersons();

	void deletePerson(final String firstName, final String LastName);

	Person savePerson(Person person);


}
