package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.FireStationAlert;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireStationAlertService;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IMedicalRecordService;

@ExtendWith(MockitoExtension.class)
public class FireStationAlertServiceImplTest {
	
	private IFireStationAlertService fireStationAlertService;
	private DataForRequest dataForRequest;
	
	@BeforeEach
	public void init() {
		JsonData jsonData = new JsonData(new ArrayList<Person>(),new ArrayList<FireStation>(),new ArrayList<MedicalRecord>());
		jsonData.getPersons().addAll(DBConstants.PERSON_DATA_TEST);
		jsonData.getFireStations().addAll(DBConstants.FIRESTATION_DATA_TEST);
		jsonData.getMedicalRecords().addAll(DBConstants.MEDICALRECORD_DATA_TEST);
		IFireStationService fireStationService = new FireStationServiceImpl(jsonData);
		IMedicalRecordService medicalRecordService = new MedicalRecordServiceImpl(jsonData);
		dataForRequest = new DataForRequest(jsonData, fireStationService, medicalRecordService);
		fireStationAlertService = new FireStationAlertServiceImpl(dataForRequest);
	}
	
	@Test
	public void getfireStationAlertList_success () {
		
		// WHEN
		final FireStationAlert resultList = fireStationAlertService.getFireStationsAlert("3");

		// THEN
		assertThat(resultList.getAdultNumber()).isEqualTo(2);
		assertThat(resultList.getChildNumber()).isEqualTo(1);
		assertThat(resultList.getFireStationAlertPersonList())
			.filteredOn(p -> p.getFirstName().contains("Ric"))
			.filteredOn(p -> p.getFirstName().contains("Nery"))
			.filteredOn(p -> p.getFirstName().contains("Arthur"));
	}
	
	@Test
	public void getfireStationAlertList_no_answer () {
		
		// WHEN
		final FireStationAlert resultList = fireStationAlertService.getFireStationsAlert("5");

		// THEN
		assertThat(resultList.getAdultNumber()).isEqualTo(0);
		assertThat(resultList.getChildNumber()).isEqualTo(0);
		assertThat(resultList.getFireStationAlertPersonList()).isEmpty();
	}


}
