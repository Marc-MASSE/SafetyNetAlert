package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBTest;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IPersonService;

public class PersonServiceImplTest {

	private IPersonService personService;
	private JsonData jsonData;
	
	private Person person1 = new Person("Marc","Sagar","9 rue de Framboisy","Limoges","87000","12-34-56-78-90","sage@email.com");
	private Person person3 = new Person("Mel","Mandinos","3 rue de Framboisy","Limoges","87000","12-34-56-78-30","barde@email.com");
	private Person person5 = new Person("Nery","Bald","4 rue de Framboisy","Limoges","87000","12-34-56-78-45","nery@email.com");
	private Person person7 = new Person("Mel","Mandinos","7 rue de Framboisy","Limoges","87000","12-34-56-78-98","bardesenior@email.com");
	private Person personToAdd = new Person("Adeline","Plus","1 rue de Chanteloup","Limoges","87000","12-34-56-78-00","adplus@email.com");
	
	@BeforeEach
	public void init() {
		jsonData = new JsonData();
		jsonData.setPersons(new ArrayList<Person>());
		jsonData.getPersons().addAll(DBTest.getPersonList());
		personService = new PersonServiceImpl(jsonData);
	}

	@Test
	public void getPersons_success() {

		// WHEN
		final List<Person> resultList = personService.getPersons();

		// THEN
		assertThat(resultList.get(0).equals(person1));
		assertThat(resultList.get(4).equals(person5));
		assertThat(resultList.get(6).equals(person7));
	}

	@Nested
	class GetPerson {

		@Test
		public void getPerson_success() {

			// WHEN
			final Person resultPerson = personService.getPerson("Marc","Sagar");

			// THEN
			assertThat(resultPerson.equals(person1));
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
			personService.deletePerson("Mel","Mandinos");

			// THEN
			assertThat(jsonData.getPersons()).doesNotContain(person3);
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
		
		assertThat(jsonData.getPersons()).doesNotContain(personToAdd);
		
		// WHEN
		personService.savePerson(personToAdd);

		// THEN
		assertThat(jsonData.getPersons()).contains(personToAdd);
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
			
			assertThat(jsonData.getPersons().get(0).equals(person1));
			
			// WHEN
			personService.updatePerson("Marc","Sagar",personToAdd);

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
			Person personToUpdate = personService.updatePerson("Nemo","Personne",personToAdd);

			// THEN
			assertThat(personToUpdate).isNull();
		}
	}
	
}
