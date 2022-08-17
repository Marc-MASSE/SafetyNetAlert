package fr.marc.safetynetalert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.service.IMedicalRecordService;

@RestController
public class MedicalRecordController {
	
	@Autowired
    IMedicalRecordService medicalRecordService;
	
    @GetMapping("/medicalRecords")
    public Iterable<MedicalRecord> getMedicalRecords() {
    	
        return medicalRecordService.getMedicalRecords();
    }

}
