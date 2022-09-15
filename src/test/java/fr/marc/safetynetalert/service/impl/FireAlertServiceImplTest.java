package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.constants.DBTest;
import fr.marc.safetynetalert.model.FireAlert;
import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireAlertService;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IMedicalRecordService;

public class FireAlertServiceImplTest {
	
	private IFireAlertService fireAlertService;
	private DataForRequest dataForRequest;
	
	@BeforeEach
	public void init() {
		//JsonData jsonData = new JsonData(new ArrayList<Person>(),new ArrayList<FireStation>(),new ArrayList<MedicalRecord>());
		JsonData jsonData = new JsonData();
		//jsonData.getPersons().addAll(DBConstants.PERSON_DATA_TEST);
		//jsonData.getFireStations().addAll(DBConstants.FIRESTATION_DATA_TEST);
		//jsonData.getMedicalRecords().addAll(DBConstants.MEDICALRECORD_DATA_TEST);
		
		jsonData.setPersons(DBTest.getPersonList());
		jsonData.setFireStations(DBTest.getFireStationList());
		jsonData.setMedicalRecords(DBTest.getMedicalRecordList());
		
		IFireStationService fireStationService = new FireStationServiceImpl(jsonData);
		IMedicalRecordService medicalRecordService = new MedicalRecordServiceImpl(jsonData);
		dataForRequest = new DataForRequest(jsonData, fireStationService, medicalRecordService);
		fireAlertService = new FireAlertServiceImpl(dataForRequest);
	}	
	
	@Test
	public void getFireAlertList_success () {
		
		// WHEN
		final List<FireAlert> resultList = fireAlertService.getFireAlertList("3 rue de Framboisy");

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
		final List<FireAlert> resultList = fireAlertService.getFireAlertList("chemin de traverse");

		// THEN
		assertThat(resultList).isEmpty();
	}

}
