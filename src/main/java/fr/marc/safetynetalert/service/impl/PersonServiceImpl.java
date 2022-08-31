package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IPersonService;

/*
 * 
 * 
 * @Author Marc
 */

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	JsonData jsonData;

	/*
	 * @return A single person according to his firstName and lastName
	 */
	@Override
	public Person getPerson(String firstName, String lastName, List<Person> dataBase) {

		Optional<Person> matchingPerson = dataBase
				.stream()
				.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
				.findFirst();
		return matchingPerson.orElse(null);
	}

	/*
	 * @return the total persons list
	 */
	@Override
	public List<Person> getPersons(List<Person> dataBase) {

		return dataBase;
	}

	@Override
	public void deletePerson(String firstName, String lastName, List<Person> dataBase) {

		Optional<Person> matchingPerson = dataBase
				.stream()
				.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
				.findFirst();
		if (matchingPerson.isPresent()) {
			dataBase.remove(matchingPerson.get());
		}

	}

	@Override
	public Person savePerson(Person person, List<Person> dataBase) {
	
		dataBase.add(person);

		return person;
	}

	@Override
	public List<String> getEmailByCity(String city, List<Person> dataBase) {
		
		List<String> emailList = new ArrayList<>();
		
		List<Person> personList = dataBase
				.stream()
				.filter(p -> p.getCity().equals(city))
				.toList();
		
		personList.forEach(p -> emailList.add(p.getEmail()));

		return emailList;
	}

	@Override
	public Person updatePerson(String firstName, String lastName, Person person, List<Person> dataBase) {

		Optional<Person> matchingPerson = dataBase
				.stream()
				.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
				.findFirst();
		if (matchingPerson.isPresent()) {
			matchingPerson.get().setAddress(person.getAddress());
			matchingPerson.get().setCity(person.getCity());
			matchingPerson.get().setZip(person.getZip());
			matchingPerson.get().setPhone(person.getPhone());
			matchingPerson.get().setEmail(person.getEmail());
			
			return matchingPerson.get();
		}
		
		return null;
	}

	
}
