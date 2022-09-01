package fr.marc.safetynetalert.service;

import java.time.LocalDate;
import java.util.List;

import fr.marc.safetynetalert.model.MedicalRecord;

public interface IMedicalRecordService {
	
	List<MedicalRecord> getMedicalRecords();
	
	MedicalRecord getMedicalRecord(final String firstName, final String lastName);
	
	int getPersonsAge(final String firstName, final String lastName, final LocalDate date);

	void deleteMedicalRecord(final String firstName, final String lastName);
	
	MedicalRecord saveMedicalRecord(final MedicalRecord medicalRecord);
	
	MedicalRecord updateMedicalRecord(final String firstName, final String lastName, final MedicalRecord medicalRecord);

}
