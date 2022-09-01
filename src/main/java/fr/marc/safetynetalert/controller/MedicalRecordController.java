package fr.marc.safetynetalert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IMedicalRecordService;

@RestController
public class MedicalRecordController {
	
    private IMedicalRecordService medicalRecordService;
	
	@Autowired
	public MedicalRecordController(IMedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}
	
    @GetMapping("/medicalRecords")
    public Iterable<MedicalRecord> getMedicalRecords() {
    	
        return medicalRecordService.getMedicalRecords();
    }
    
    @GetMapping("/medicalRecord")
    public MedicalRecord getMedicalRecordByParam(@RequestParam String firstName, @RequestParam String lastName) {
     	
        return medicalRecordService.getMedicalRecord(firstName, lastName);
    }
    
    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecordByParam(@RequestParam String firstName, @RequestParam String lastName) {
     	
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
    
    @PostMapping(value = "/medicalRecord")
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
    	return medicalRecordService.saveMedicalRecord(medicalRecord);
    }
    
    @PutMapping(value = "/medicalRecord")
    public MedicalRecord updatePerson(@RequestParam String firstName, @RequestParam String lastName,@RequestBody MedicalRecord medicalRecord) {
    	return medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecord);
      
    }

}
