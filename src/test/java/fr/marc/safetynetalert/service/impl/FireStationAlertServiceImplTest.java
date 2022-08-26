package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.FireStationAlert;

public class FireStationAlertServiceImplTest {
	
	FireStationAlertServiceImpl fireStationAlertServiceImpl = new FireStationAlertServiceImpl();
	
	@Test
	public void getPhoneAlertList_success () {
		
		// WHEN
		final FireStationAlert resultList = fireStationAlertServiceImpl.getFireStationsAlert("3",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList.getAdultNumber()).isEqualTo(2);
		assertThat(resultList.getChildNumber()).isEqualTo(1);
		assertThat(resultList.getFireStationAlertPersonList())
			.filteredOn(p -> p.getFirstName().contains("Ric"))
			.filteredOn(p -> p.getFirstName().contains("Nery"))
			.filteredOn(p -> p.getFirstName().contains("Arthur"));
	}
	
	@Test
	public void getPhoneAlertList_no_answer () {
		
		// WHEN
		final FireStationAlert resultList = fireStationAlertServiceImpl.getFireStationsAlert("5",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList.getAdultNumber()).isEqualTo(0);
		assertThat(resultList.getChildNumber()).isEqualTo(0);
		assertThat(resultList.getFireStationAlertPersonList()).isEmpty();
	}


}
