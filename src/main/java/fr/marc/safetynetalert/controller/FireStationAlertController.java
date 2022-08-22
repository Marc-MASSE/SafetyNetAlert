package fr.marc.safetynetalert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.FireStationAlert;
import fr.marc.safetynetalert.service.IFireStationAlertService;

@RestController
public class FireStationAlertController {
	
	@Autowired
	IFireStationAlertService fireStationAlertService;
	
	   @GetMapping("/firestation")
	    public FireStationAlert getFireStationAlertData(@RequestParam String stationNumber) {
	     	
	        return fireStationAlertService.getFireStationsAlert(stationNumber);
	    }
	

}
