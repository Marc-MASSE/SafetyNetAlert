package fr.marc.safetynetalert.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.JsonData;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.service.IMedicalRecordService;

@Service
@Component
public class MedicalRecordServiceImpl implements IMedicalRecordService {
	
	@Autowired
	JsonData jsonData;

	@Override
	public Iterable<MedicalRecord> getMedicalRecords() {
		// TODO Auto-generated method stub
		return jsonData.getMedicalRecords();
	}

	@Override
	public void deleteMedicalRecord(String firstName, String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		// TODO Auto-generated method stub
		
		return medicalRecord;
	}
	

}
