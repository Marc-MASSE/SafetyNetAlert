package fr.marc.safetynetalert.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.marc.safetynetalert.model.JsonData;
import fr.marc.safetynetalert.repository.Retrieval;

@Component
public class JsonDataService {
	
	@Autowired
	JsonData jsonData;
	
	public void initialization() throws IOException {
		
		jsonData.setPersons(Retrieval.personsList());
		jsonData.setFireStations(Retrieval.fireStationsList());
		jsonData.setMedicalRecords(Retrieval.medicalRecordsList());
		}

}
