package fr.marc.safetynetalert.service;

import java.time.LocalDate;

import fr.marc.safetynetalert.model.MedicalRecord;

public interface IMedicalRecordService {
	
	Iterable<MedicalRecord> getMedicalRecords();
	
	MedicalRecord getMedicalRecord(final String firstName, final String lastName);
	
	int getPersonsAge(final String firstName, final String lastName, final LocalDate date);

	void deleteMedicalRecord(final String firstName, final String lastName);
	
	MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

}
