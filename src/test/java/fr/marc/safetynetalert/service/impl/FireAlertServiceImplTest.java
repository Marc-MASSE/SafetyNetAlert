package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.FireAlert;

public class FireAlertServiceImplTest {
	
	FireAlertServiceImpl fireAlertServiceImpl = new FireAlertServiceImpl();
	
	@Test
	public void getFireAlertList_success () {
		
		// WHEN
		final List<FireAlert> resultList = fireAlertServiceImpl.getFireAlertList("3 rue de Framboisy",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList.get(0).getFirstName()).isEqualTo("Mel");
		assertThat(resultList.get(0).getLastName()).isEqualTo("Mandinos");
		assertThat(resultList.get(0).getPhone()).isEqualTo("12-34-56-78-30");
		assertThat(resultList.get(0).getAge()).isEqualTo(24);
		assertThat(resultList.get(0).getMedications()).contains("Aspégic : 500mg");
		assertThat(resultList.get(0).getAllergies()).contains("Shellfish");
		assertThat(resultList.get(0).getStationNumber()).isEqualTo("2");
	}
	
	@Test
	public void getFireAlertList_no_answer () {
		
		// WHEN
		final List<FireAlert> resultList = fireAlertServiceImpl.getFireAlertList("chemin de traverse",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList).isEmpty();
	}

}
