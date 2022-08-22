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
	 * @return - a single person according to his firstName and lastName
	 */
	@Override
	public Person getPerson(String firstName, String lastName) {

		Optional<Person> matchingPerson = jsonData.getPersons()
				.stream()
				.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
				.findFirst();
		return matchingPerson.orElse(null);
	}

	/*
	 * @return the total persons list
	 */
	@Override
	public List<Person> getPersons() {

		return jsonData.getPersons();
	}

	@Override
	public void deletePerson(String firstName, String lastName) {
		// TODO Auto-generated method stub

		Optional<Person> matchingPerson = jsonData.getPersons()
				.stream()
				.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
				.findFirst();
		if (matchingPerson.isPresent()) {
			jsonData.getPersons().remove(matchingPerson.get());
		}

	}

	@Override
	public Person savePerson(Person person) {
	
		jsonData.getPersons().add(person);

		return person;
	}

	@Override
	public List<Person> getPersonsByAddress(String address) {
		// TODO Auto-generated method stub
		List<Person> personList = jsonData.getPersons()
				.stream()
				.filter(p -> p.getAddress().equals(address))
				.toList();

		return personList;
	}
	
	@Override
	public List<String> getEmailByCity(String city) {
		// TODO Auto-generated method stub
		List<String> emailList = new ArrayList<>();
		
		List<Person> personList = jsonData.getPersons()
				.stream()
				.filter(p -> p.getCity().equals(city))
				.toList();
		
		personList.forEach(p -> emailList.add(p.getEmail()));

		return emailList;
	}

	@Override
	public Person updatePerson(String firstName, String lastName, Person person) {

		Optional<Person> matchingPerson = jsonData.getPersons()
				.stream()
				.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
				.findFirst();
		if (matchingPerson.isPresent()) {
			matchingPerson.get().setAddress(person.getAddress());
			
			return matchingPerson.get();
		}
		
		return null;
	}

	
}
