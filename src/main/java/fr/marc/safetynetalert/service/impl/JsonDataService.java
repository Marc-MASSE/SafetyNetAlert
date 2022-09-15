package fr.marc.safetynetalert.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.repository.Retrieval;

/*
 * Class used to initialize jsonData with data contained in data.json
 * Theses data are retrieved by Retrieval class.
 */

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
