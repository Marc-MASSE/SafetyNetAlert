package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBTest;
import fr.marc.safetynetalert.model.ChildAlert;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IChildAlertService;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IMedicalRecordService;

public class ChildAlertServiceImplTest {
	
	private IChildAlertService childAlertService;
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
		childAlertService = new ChildAlertServiceImpl(dataForRequest);
	}	
	
	@Test
	public void getChildAlertList_success () {
		
		// WHEN
		final ChildAlert resultList = childAlertService.getChildAlertList("4 rue de Framboisy");

		// THEN
		assertThat(resultList.getChild())
			.filteredOn(c -> c.getFirstName().contains("Ric"));
		assertThat(resultList.getOtherMember())
			.filteredOn(c -> c.getFirstName().contains("Nery"))
			.filteredOn(c -> c.getFirstName().contains("Arthur"));
	}
	
	@Test
	public void getChildAlertList_no_child () {
		
		// WHEN
		final ChildAlert resultList = childAlertService.getChildAlertList("9 rue de Framboisy");

		// THEN
		assertThat(resultList.getChild()).isNull();
		assertThat(resultList.getOtherMember()).isNull();
	}

	@Test
	public void getChildAlertList_no_answer () {
		
		// WHEN
		final ChildAlert resultList = childAlertService.getChildAlertList("chemin de traverse");

		// THEN
		assertThat(resultList.getChild()).isNull();
		assertThat(resultList.getOtherMember()).isNull();
	}

}
