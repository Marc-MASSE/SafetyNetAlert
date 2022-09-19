package fr.marc.safetynetalert.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;

@Component
public class JsonData {
	
	private List<Person> persons;
	private List<FireStation> fireStations;
	private List<MedicalRecord> medicalRecords;
	

	public JsonData() {
	}
	
	
	public List<Person> getPersons() {
		return persons;
	}


	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}


	public List<FireStation> getFireStations() {
		return fireStations;
	}


	public void setFireStations(List<FireStation> fireStations) {
		this.fireStations = fireStations;
	}


	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}


	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	
	

}
