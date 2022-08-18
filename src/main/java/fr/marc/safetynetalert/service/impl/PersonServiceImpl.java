package fr.marc.safetynetalert.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.JsonData;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.service.IPersonService;

/*
 * 
 * 
 * @Author Marc
 */

@Service
@Component
public class PersonServiceImpl implements IPersonService {

	@Autowired
	JsonData jsonData;

	/*
	 * @param - firstName and lastName
	 * 
	 * @return - a single person according to his firstName and lastName
	 */
	@Override
	public Person getPerson(String firstName, String lastName) {

		Optional<Person> matchingPerson = jsonData.getPersons()
				.stream()
				.filter(p -> p.getFirstName().equals(firstName) && p.getLastName()
				.equals(lastName))
				.findFirst();
		return matchingPerson.orElse(null);
	}

	/*
	 * @return the total persons list
	 */
	@Override
	public Iterable<Person> getPersons() {

		return jsonData.getPersons();
	}

	@Override
	public void deletePerson(String firstName, String lastName) {
		// TODO Auto-generated method stub
		
		
				
		Optional<Person> matchingPerson = jsonData.getPersons()
				.stream()
				.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
				.findFirst();
		if(matchingPerson.isPresent()) {
			jsonData.getPersons().remove(matchingPerson.get());
		}
		
		
		
		/*
		jsonData.getPersons().forEach(p -> {
			if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				jsonData.getPersons().remove(p);
			}
		});
		*/
		
	}

	@Override
	public Person savePerson(Person person) {
		// TODO Auto-generated method stub
		
		return person;
	}

	@Override
	public List<Person> getPersonsByAddress(String address) {
		// TODO Auto-generated method stub
		List<Person> personList = jsonData.getPersons().stream().filter(p->
		p.getAddress().equals(address)).toList();
		
		return personList;
	}

}
