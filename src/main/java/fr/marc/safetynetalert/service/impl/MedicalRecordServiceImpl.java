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

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {
	
	@Autowired
	JsonData jsonData;
	
	private AgeCalculator ageCalculator = new AgeCalculator();


	@Override
	public List<MedicalRecord> getMedicalRecords(List<MedicalRecord> dataBase) {
		
		return dataBase;
	}
	
	@Override
	public MedicalRecord getMedicalRecord(String firstName, String lastName, List<MedicalRecord> dataBase) {
		
		Optional<MedicalRecord> matchingMedicalRecord = dataBase
				.stream()
				.filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
				.findFirst();
		
		return matchingMedicalRecord.orElse(new MedicalRecord());
	}

	@Override
	public void deleteMedicalRecord(String firstName, String lastName, List<MedicalRecord> dataBase) {
		
		Optional<MedicalRecord> matchingMedicalRecord = dataBase
				.stream()
				.filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
				.findFirst();
		if (matchingMedicalRecord.isPresent()) {
			dataBase.remove(matchingMedicalRecord.get());
		}
	}

	@Override
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord, List<MedicalRecord> dataBase) {
		
		dataBase.add(medicalRecord);
		
		return medicalRecord;
	}


	/*
	 * Calculates the age of a person identified by their first name and last name on the given date.
	 * @return the person's age or -1 if the birth date does'nt exist
	 */
	@Override
	public int getPersonsAge(String firstName, String lastName, LocalDate date, List<MedicalRecord> dataBase) {
		
		int age;
		
		MedicalRecord matchingMedicalRecord = dataBase
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
	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord, List<MedicalRecord> dataBase) {
		
		Optional<MedicalRecord> matchingMedicalRecord = dataBase
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
