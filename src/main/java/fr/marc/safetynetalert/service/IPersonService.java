package fr.marc.safetynetalert.service;

import java.util.List;

import fr.marc.safetynetalert.model.Person;

public interface IPersonService {
	
	Person getPerson(final String firstName, final String lastName);

	List<Person> getPersons();
	
	// List<Person> getPersonsByAddress(final String address);

	void deletePerson(final String firstName, final String lastName);

	Person savePerson(final Person person);

	Person updatePerson(final String firstName, final String lastName, final Person person);

	List<String> getEmailByCity(final String city);

}
