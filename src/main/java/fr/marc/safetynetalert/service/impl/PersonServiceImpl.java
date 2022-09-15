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
 * Class used for endpoint /person
 * 
 * For updating person's data list
 */

@Service
public class PersonServiceImpl implements IPersonService {

	private JsonData jsonData;

	@Autowired
	public PersonServiceImpl(JsonData jsonData) {
		this.jsonData = jsonData;
	}
	
	/*
	 * @return A single person according to his firstName and lastName
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

	
	/*
	* Class used for URL /communityEmail?city=<city>
	* 
	* @param city
	* @return list of email of people who live in this city
	*/
	@Override
	public List<String> getEmailByCity(String city) {
		
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
			matchingPerson.get().setCity(person.getCity());
			matchingPerson.get().setZip(person.getZip());
			matchingPerson.get().setPhone(person.getPhone());
			matchingPerson.get().setEmail(person.getEmail());
			
			return matchingPerson.get();
		}
		
		return null;
	}

	
}
