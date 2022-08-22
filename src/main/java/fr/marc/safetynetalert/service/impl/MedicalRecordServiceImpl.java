package fr.marc.safetynetalert.service.impl;

import java.time.LocalDate;
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
	public Iterable<MedicalRecord> getMedicalRecords() {
		
		return jsonData.getMedicalRecords();
	}

	@Override
	public void deleteMedicalRecord(String firstName, String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		
		return medicalRecord;
	}

	@Override
	public MedicalRecord getMedicalRecord(String firstName, String lastName) {
		
		Optional<MedicalRecord> matchingMedicalRecord = jsonData.getMedicalRecords()
				.stream()
				.filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
				.findFirst();
		
		return matchingMedicalRecord.orElse(null);
	}

	@Override
	public int getPersonsAge(String firstName, String lastName, LocalDate date) {
		
		Optional<MedicalRecord> matchingMedicalRecord = jsonData.getMedicalRecords()
				.stream()
				.filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
				.findFirst();
	
		return ageCalculator.getAge(matchingMedicalRecord.get().getBirthdate().toString(),date);
	}
	

}
