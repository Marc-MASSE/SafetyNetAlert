package fr.marc.safetynetalert.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Component;

import fr.marc.safetynetalert.model.JsonData;
import fr.marc.safetynetalert.repository.Retrieval;

@Component
public class JsonDataService {
	
	private final JsonData jsonData;
	
	
	
	public JsonDataService(JsonData jsonData) {
		this.jsonData = jsonData;
	}


	public void initialization() throws IOException {
		
		jsonData.setPersons(Retrieval.personsList());
		jsonData.setFireStations(Retrieval.fireStationsList());
		jsonData.setMedicalRecords(Retrieval.medicalRecordsList());
		}

}
