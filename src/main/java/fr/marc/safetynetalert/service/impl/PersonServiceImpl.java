package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.service.IPersonService;

/*
 * 
 * 
 * @Author Marc
 */

@Service
public class PersonServiceImpl implements IPersonService {

	/*
	 * @param - firstName and lastName
	 * 
	 * @return - a single person according to his firstName and lastName
	 * 
	 */

	@Override
	public List<Person> getPerson(String firstName, String lastName) {
		// TODO Auto-generated method stub
		List<Person> persons = new ArrayList<>();
		List<Person> personsFinded = new ArrayList<>();

		persons = Extract.listOfPersons();

		persons.forEach(p -> {
			if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				personsFinded.add(p);
			}
		});
		return personsFinded;
	}

	/*
	 * @return the total persons list
	 */
	@Override
	public Iterable<Person> getPersons() {
		return Extract.listOfPersons();
	}

	@Override
	public void deletePerson(String firstName, String LastName) {
		// TODO Auto-generated method stub

	}

	@Override
	public Person savePerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public Optional<Person> getPerson(final Long id) { return
	 * personRepository.findById(id); }
	 * 
	 * public Iterable<Person> getPersons() { return personRepository.findAll(); }
	 * 
	 * public void deletePerson(final Long id) { personRepository.deleteById(id); }
	 * 
	 * public Person savePerson(Person person) { Person savedPerson =
	 * personRepository.save(person); return savedPerson; }
	 */

}
