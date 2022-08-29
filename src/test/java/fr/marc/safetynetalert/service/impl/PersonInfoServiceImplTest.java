package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.PersonInfo;

public class PersonInfoServiceImplTest {
	
	PersonInfoServiceImpl personInfoServiceImpl = new PersonInfoServiceImpl();
	
	@Test
	public void getPersonInfoList_success () {
		
		// WHEN
		final List<PersonInfo> resultList = personInfoServiceImpl.getPersonInfoList("Mel","Mandinos",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList.get(0).getFirstName()).isEqualTo("Mel");
		assertThat(resultList.get(0).getLastName()).isEqualTo("Mandinos");
		assertThat(resultList.get(0).getAge()).isEqualTo(24);
		assertThat(resultList.get(0).getMedications()).contains("Aspégic : 500mg");
		assertThat(resultList.get(0).getAllergies()).contains("Shellfish");
		assertThat(resultList.get(1).getFirstName()).isEqualTo("Mel");
		assertThat(resultList.get(1).getLastName()).isEqualTo("Mandinos");
		assertThat(resultList.get(1).getAge()).isEqualTo(72);
		assertThat(resultList.get(1).getMedications()).contains("Doliprane : 500mg");
		assertThat(resultList.get(1).getAllergies()).contains("Retirement");
	}
	
	@Test
	public void getFireAlertList_no_answer () {
		
		// WHEN
		final List<PersonInfo> resultList = personInfoServiceImpl.getPersonInfoList("Nemo","Personne",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList).isEmpty();
	}

}
