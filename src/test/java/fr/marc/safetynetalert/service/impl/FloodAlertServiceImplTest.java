package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.FloodAlert;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IFloodAlertService;
import fr.marc.safetynetalert.service.IMedicalRecordService;

public class FloodAlertServiceImplTest {
	
	private IFloodAlertService floodAlertService;
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
		floodAlertService = new FloodAlertServiceImpl(dataForRequest);
	}
	
	@Test
	public void getFloodAlertList_success () {
		
		// WHEN
		final List<FloodAlert> resultList = floodAlertService.getFloodAlertList(List.of("1","2"));

		// THEN
		assertThat(resultList.get(0).getStationNumber()).isEqualTo("1");
		assertThat(resultList.get(0).getFloodAlertPerStation())
			.filteredOn(f -> f.getFirstName().contains("Marc"))
			.filteredOn(f -> f.getFirstName().contains("Françoise"));
		assertThat(resultList.get(1).getStationNumber()).isEqualTo("2");
		assertThat(resultList.get(1).getFloodAlertPerStation())
			.filteredOn(f -> f.getFirstName().contains("Mel"));
	}
	
	@Test
	public void getFloodAlertList_no_answer () {
		
		// WHEN
		final List<FloodAlert> resultList = floodAlertService.getFloodAlertList(List.of("5"));

		// THEN
		assertThat(resultList.get(0).getStationNumber()).isEqualTo("5");
		assertThat(resultList.get(0).getFloodAlertPerStation()).isEmpty();
	}

	

}
