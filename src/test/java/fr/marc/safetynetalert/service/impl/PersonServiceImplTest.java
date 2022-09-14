package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IPersonService;

public class PersonServiceImplTest {

	private IPersonService personService;
	private JsonData jsonData;
	
	
	@BeforeEach
	public void init() {
		jsonData = new JsonData();
		jsonData.setPersons(new ArrayList<Person>());
		jsonData.getPersons().addAll(DBConstants.PERSON_DATA_TEST);
		personService = new PersonServiceImpl(jsonData);
	}

	@Test
	public void getPersons_success() {

		// WHEN
		final List<Person> resultList = personService.getPersons();

		// THEN
		assertThat(resultList.get(0).equals(DBConstants.person1));
		assertThat(resultList.get(4).equals(DBConstants.person5));
		assertThat(resultList.get(6).equals(DBConstants.person7));
	}

	@Nested
	class GetPerson {

		@Test
		public void getPerson_success() {

			// WHEN
			final Person resultPerson = personService.getPerson("Marc","Sagar");

			// THEN
			assertThat(resultPerson.equals(DBConstants.person1));
		}

		@Test
		public void getPerson_no_answer() {

			// WHEN
			final Person resultPerson = personService.getPerson("Nemo","Personne");

			// THEN
			assertThat(resultPerson).isNull();
		}
	}

	@Nested
	class DeletePerson {

		@Test
		public void deletePerson_success() {

			// WHEN
			personService.deletePerson("Marc","Sagar");

			// THEN
			assertThat(jsonData.getPersons()).doesNotContain(DBConstants.person1);
		}

		@Test
		public void deletePerson_does_not_exist() {

			// WHEN
			personService.deletePerson("Nemo","Personne");

			// THEN
			assertThat(jsonData.getPersons())
				.filteredOn(p -> p.getFirstName().equals("Nemo"))
				.isEmpty();;
		}
	}
	
	@Test
	public void savePerson_success() {
		
		assertThat(jsonData.getPersons()).doesNotContain(DBConstants.personToAdd);
		
		// WHEN
		personService.savePerson(DBConstants.personToAdd);

		// THEN
		assertThat(jsonData.getPersons()).contains(DBConstants.personToAdd);
	}
	
	@Nested
	class GetEmailByCity {

		@Test
		public void getEmail_success() {
			
			// WHEN
			List<String> emailData = personService.getEmailByCity("Limoges");

			// THEN
			assertThat(emailData.get(0).equals("sage@email.com"));
			assertThat(emailData.get(3).equals("ric@email.com"));
			assertThat(emailData.get(6).equals("bardesenior@email.com"));
		}

		@Test
		public void getEmail_no_answer() {

			// WHEN
			List<String> emailData = personService.getEmailByCity("Trifouilly");

			// THEN
			assertThat(emailData).isEmpty();;
		}
	}
	
	@Nested
	class UpdatePerson {

		@Test
		public void updatePerson_success() {
			
			// WHEN
			personService.updatePerson("Marc","Sagar",DBConstants.personToAdd);

			// THEN
			// The entire person is updated except his firstName and lastName
			assertThat(jsonData.getPersons().get(0).getFirstName().equals("Marc"));
			assertThat(jsonData.getPersons().get(0).getLastName().equals("Sagar"));
			assertThat(jsonData.getPersons().get(0).getAddress().equals("1 rue de Chanteloup"));
			assertThat(jsonData.getPersons().get(0).getPhone().equals("12-34-56-78-00"));
			assertThat(jsonData.getPersons().get(0).getEmail().equals("adplus@email.com"));
		}

		@Test
		public void updatePerson_does_not_exist() {
			
			// WHEN
			Person personToUpdate = personService.updatePerson("Nemo","Personne",DBConstants.personToAdd);

			// THEN
			assertThat(personToUpdate).isNull();
		}
	}
	
}
