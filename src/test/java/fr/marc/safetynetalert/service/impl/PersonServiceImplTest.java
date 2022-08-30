package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.Person;

public class PersonServiceImplTest {

	PersonServiceImpl personServiceImpl = new PersonServiceImpl();

	@Test
	public void getPersons_success() {

		// WHEN
		final List<Person> resultList = personServiceImpl.getPersons(DBConstants.PERSON_DATA_TEST);

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
			final Person resultPerson = personServiceImpl.getPerson("Marc","Sagar",DBConstants.PERSON_DATA_TEST);

			// THEN
			assertThat(resultPerson.equals(DBConstants.person1));
		}

		@Test
		public void getPerson_no_answer() {

			// WHEN
			final Person resultPerson = personServiceImpl.getPerson("Nemo","Personne",DBConstants.PERSON_DATA_TEST);

			// THEN
			assertThat(resultPerson).isNull();
		}
	}

	@Nested
	class DeletePerson {

		@Test
		public void deletePerson_success() {
			
			//GIVEN
			List<Person> personData = new ArrayList<>();
			personData.addAll(DBConstants.PERSON_DATA_TEST);

			// WHEN
			personServiceImpl.deletePerson("Marc","Sagar",personData);

			// THEN
			assertThat(personData).doesNotContain(DBConstants.person1);
		}

		@Test
		public void deletePerson_does_not_exist() {

			//GIVEN
			List<Person> personData = new ArrayList<>();
			personData.addAll(DBConstants.PERSON_DATA_TEST);

			// WHEN
			personServiceImpl.deletePerson("Nemo","Personne",personData);

			// THEN
			assertThat(personData)
				.filteredOn(p -> p.getFirstName().equals("Nemo"))
				.isEmpty();;
		}
	}
	
	@Test
	public void savePerson_success() {
		
		//GIVEN
		List<Person> personData = new ArrayList<>();
		personData.addAll(DBConstants.PERSON_DATA_TEST);

		// WHEN
		personServiceImpl.savePerson(DBConstants.personToAdd,personData);

		// THEN
		assertThat(personData).contains(DBConstants.personToAdd);
	}
	
	@Nested
	class GetEmailByCity {

		@Test
		public void getEmail_success() {
			
			// WHEN
			List<String> emailData = personServiceImpl.getEmailByCity("Limoges",DBConstants.PERSON_DATA_TEST);

			// THEN
			assertThat(emailData.get(0).equals("sage@email.com"));
			assertThat(emailData.get(3).equals("ric@email.com"));
			assertThat(emailData.get(6).equals("bardesenior@email.com"));
		}

		@Test
		public void getEmail_no_answer() {

			// WHEN
			List<String> emailData = personServiceImpl.getEmailByCity("Trifouilly",DBConstants.PERSON_DATA_TEST);

			// THEN
			assertThat(emailData).isEmpty();;
		}
	}
	
	@Nested
	class UpdatePerson {

		@Test
		public void updatePerson_success() {
			
			//GIVEN
			List<Person> personData = new ArrayList<>();
			personData.addAll(DBConstants.PERSON_DATA_TEST);
			
			// WHEN
			personServiceImpl.updatePerson("Marc","Sagar",DBConstants.personToAdd,personData);

			// THEN
			// The entire person is updated except his firstName and lastName
			assertThat(personData.get(0).getFirstName().equals("Marc"));
			assertThat(personData.get(0).getLastName().equals("Sagar"));
			assertThat(personData.get(0).getAddress().equals("1 rue de Chanteloup"));
			assertThat(personData.get(0).getPhone().equals("12-34-56-78-00"));
			assertThat(personData.get(0).getEmail().equals("adplus@email.com"));
		}

		@Test
		public void updatePerson_does_not_exist() {

			//GIVEN
			List<Person> personData = new ArrayList<>();
			personData.addAll(DBConstants.PERSON_DATA_TEST);
			
			// WHEN
			Person personToUpdate = personServiceImpl.updatePerson("Nemo","Personne",DBConstants.personToAdd,personData);

			// THEN
			assertThat(personToUpdate).isNull();
		}
	}
	
}
