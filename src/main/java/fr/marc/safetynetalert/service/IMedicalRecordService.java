package fr.marc.safetynetalert.service;

import fr.marc.safetynetalert.model.MedicalRecord;

public interface IMedicalRecordService {
	
	Iterable<MedicalRecord> getMedicalRecords();

	void deleteMedicalRecord(final String firstName, final String lastName);
	
	
	
	MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

}
