package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IMedicalRecordService;
import fr.marc.safetynetalert.service.IPhoneAlertService;

public class PhoneAlertServiceImplTest {
	
	private IPhoneAlertService phoneAlertService;
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
		phoneAlertService = new PhoneAlertServiceImpl(dataForRequest);
	}
	
	@Test
	public void getPhoneAlertList_success () {
		
		// WHEN
		final List<String> resultList = phoneAlertService.getPhoneAlertList("2");

		// THEN
		assertThat(resultList).isEqualTo(List.of("12-34-56-78-30","12-34-56-78-98"));
	}
	
	@Test
	public void getPhoneAlertList_no_answer () {
		
		// WHEN
		final List<String> resultList = phoneAlertService.getPhoneAlertList("5");

		// THEN
		assertThat(resultList).isEmpty();
	}

}
