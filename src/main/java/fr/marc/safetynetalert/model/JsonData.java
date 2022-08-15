package fr.marc.safetynetalert.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class JsonData {
	
	private List<Person> persons;
	private List<FireStation> fireStations;
	private List<MedicalRecord> medicalRecords;
	

	public JsonData() {
	}
	

	public JsonData(List<Person> persons, List<FireStation> fireStations, List<MedicalRecord> medicalRecords) {
		super();
		this.persons = persons;
		this.fireStations = fireStations;
		this.medicalRecords = medicalRecords;
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
