package fr.marc.safetynetalert.service;

import java.util.List;

import fr.marc.safetynetalert.model.Person;

public interface IPersonService {
	
	Person getPerson(final String firstName, final String lastName, final List<Person> dataBase);

	List<Person> getPersons(final List<Person> dataBase);
	
	void deletePerson(final String firstName, final String lastName, final List<Person> dataBase);

	Person savePerson(final Person person, final List<Person> dataBase);

	Person updatePerson(final String firstName, final String lastName, final Person person, final List<Person> dataBase);

	List<String> getEmailByCity(final String city, final List<Person> dataBase);

}
