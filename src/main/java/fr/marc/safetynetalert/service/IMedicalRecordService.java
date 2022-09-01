package fr.marc.safetynetalert.service;

import java.time.LocalDate;
import java.util.List;

import fr.marc.safetynetalert.model.MedicalRecord;

public interface IMedicalRecordService {
	
	List<MedicalRecord> getMedicalRecords(final List<MedicalRecord> dataBase);
	
	MedicalRecord getMedicalRecord(final String firstName, final String lastName, final List<MedicalRecord> dataBase);
	
	int getPersonsAge(final String firstName, final String lastName, final LocalDate date, final List<MedicalRecord> dataBase);

	void deleteMedicalRecord(final String firstName, final String lastName, final List<MedicalRecord> dataBase);
	
	MedicalRecord saveMedicalRecord(final MedicalRecord medicalRecord, final List<MedicalRecord> dataBase);
	
	MedicalRecord updateMedicalRecord(final String firstName, final String lastName, final MedicalRecord medicalRecord, final List<MedicalRecord> dataBase);

}
