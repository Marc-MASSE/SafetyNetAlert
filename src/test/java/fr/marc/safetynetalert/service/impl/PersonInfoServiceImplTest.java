package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBTest;
import fr.marc.safetynetalert.model.PersonInfo;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IMedicalRecordService;
import fr.marc.safetynetalert.service.IPersonInfoService;

public class PersonInfoServiceImplTest {
	
	private IPersonInfoService personInfoService;
	private DataForRequest dataForRequest;
	
	@BeforeEach
	public void init() {
		JsonData jsonData = new JsonData();
		jsonData.setPersons(DBTest.getPersonList());
		jsonData.setFireStations(DBTest.getFireStationList());
		jsonData.setMedicalRecords(DBTest.getMedicalRecordList());
		
		IFireStationService fireStationService = new FireStationServiceImpl(jsonData);
		IMedicalRecordService medicalRecordService = new MedicalRecordServiceImpl(jsonData);
		dataForRequest = new DataForRequest(jsonData, fireStationService, medicalRecordService);
		personInfoService = new PersonInfoServiceImpl(dataForRequest);
	}
	
	@Test
	public void getPersonInfoList_success () {
		
		// WHEN
		final List<PersonInfo> resultList = personInfoService.getPersonInfoList("Mel","Mandinos");

		// THEN
		assertThat(resultList.get(0).getFirstName()).isEqualTo("Mel");
		assertThat(resultList.get(0).getLastName()).isEqualTo("Mandinos");
		assertThat(resultList.get(0).getAddress()).isEqualTo("3 rue de Framboisy");
		assertThat(resultList.get(0).getEmail()).isEqualTo("barde@email.com");
		assertThat(resultList.get(1).getFirstName()).isEqualTo("Mel");
		assertThat(resultList.get(1).getLastName()).isEqualTo("Mandinos");
		assertThat(resultList.get(1).getAddress()).isEqualTo("7 rue de Framboisy");
		assertThat(resultList.get(1).getEmail()).isEqualTo("bardesenior@email.com");
	}
	
	@Test
	public void getPersonInfotList_no_answer () {
		
		// WHEN
		final List<PersonInfo> resultList = personInfoService.getPersonInfoList("Nemo","Personne");

		// THEN
		assertThat(resultList).isEmpty();
	}

}
