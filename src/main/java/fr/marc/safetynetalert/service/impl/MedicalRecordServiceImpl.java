package fr.marc.safetynetalert.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IMedicalRecordService;
import fr.marc.safetynetalert.util.AgeCalculator;

/*
 * Class used for endpoint /medicalRecord
 * 
 * For updating medicalRecord's data list
 */

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {
	
	private JsonData jsonData;
	
	@Autowired
	public MedicalRecordServiceImpl(JsonData jsonData) {
		this.jsonData = jsonData;
	}
	
	private AgeCalculator ageCalculator = new AgeCalculator();


	@Override
	public List<MedicalRecord> getMedicalRecords() {
		
		return jsonData.getMedicalRecords();
	}
	
	@Override
	public MedicalRecord getMedicalRecord(String firstName, String lastName) {
		
		Optional<MedicalRecord> matchingMedicalRecord = jsonData.getMedicalRecords()
				.stream()
				.filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
				.findFirst();
		
		return matchingMedicalRecord.orElse(new MedicalRecord());
	}

	@Override
	public void deleteMedicalRecord(String firstName, String lastName) {
		
		Optional<MedicalRecord> matchingMedicalRecord = jsonData.getMedicalRecords()
				.stream()
				.filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
				.findFirst();
		if (matchingMedicalRecord.isPresent()) {
			jsonData.getMedicalRecords().remove(matchingMedicalRecord.get());
		}
	}

	@Override
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		
		jsonData.getMedicalRecords().add(medicalRecord);
		
		return medicalRecord;
	}


	/*
	 * Calculates the age of a person identified by their first name and last name on the given date.
	 * @return the person's age or -1 if the birth date does'nt exist
	 */
	@Override
	public int getPersonsAge(String firstName, String lastName, LocalDate date) {
		
		int age;
		
		MedicalRecord matchingMedicalRecord = jsonData.getMedicalRecords()
				.stream()
				.filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
				.findFirst()
				.orElse(new MedicalRecord());
		
		try {
			age = ageCalculator.getAge(matchingMedicalRecord.getBirthdate().toString(),date);
		}
		catch (Exception e) {
			age =-1;
		}
		return age;
	}

	@Override
	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord) {
		
		Optional<MedicalRecord> matchingMedicalRecord = jsonData.getMedicalRecords()
				.stream()
				.filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
				.findFirst();
		if (matchingMedicalRecord.isPresent()) {
			matchingMedicalRecord.get().setBirthdate(medicalRecord.getBirthdate());
			matchingMedicalRecord.get().setMedications(medicalRecord.getMedications());
			matchingMedicalRecord.get().setAllergies(medicalRecord.getAllergies());
			
			return matchingMedicalRecord.get();
		}
		return null;
	}

}
