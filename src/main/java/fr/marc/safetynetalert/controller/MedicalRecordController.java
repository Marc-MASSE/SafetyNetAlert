package fr.marc.safetynetalert.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	static Logger log = LogManager.getLogger(MedicalRecordController.class.getName());
	
    private IMedicalRecordService medicalRecordService;
	
	@Autowired
	public MedicalRecordController(IMedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}
	
    @GetMapping("/medicalRecords")
    public Iterable<MedicalRecord> getMedicalRecords() {
		log.info("GET request - endpoint /medicalRecords - return the entire list of medicalRecords");
        return medicalRecordService.getMedicalRecords();
    }
    
    @GetMapping("/medicalRecord")
    public MedicalRecord getMedicalRecordByParam(@RequestParam String firstName, @RequestParam String lastName) {
		log.info("GET request - endpoint /medicalRecord - firstName = "+firstName+" lastName = "+lastName);
        return medicalRecordService.getMedicalRecord(firstName, lastName);
    }
    
    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecordByParam(@RequestParam String firstName, @RequestParam String lastName) {
		log.info("DELETE request - endpoint /medicalRecord - firstName = "+firstName+" lastName = "+lastName);
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
    
    @PostMapping(value = "/medicalRecord")
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		log.info("POST request - endpoint /medicalRecord - body = "+medicalRecord);
    	return medicalRecordService.saveMedicalRecord(medicalRecord);
    }
    
    @PutMapping(value = "/medicalRecord")
    public MedicalRecord updatePerson(@RequestParam String firstName, @RequestParam String lastName,@RequestBody MedicalRecord medicalRecord) {
    	log.info("PUT request - endpoint /person - firstName = "+firstName+" lastName = "+lastName+" body = "+medicalRecord);
    	return medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecord);
      
    }

}
