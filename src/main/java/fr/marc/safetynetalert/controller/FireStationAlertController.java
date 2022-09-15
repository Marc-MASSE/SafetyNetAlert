package fr.marc.safetynetalert.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.FireStationAlert;
import fr.marc.safetynetalert.service.IFireStationAlertService;

/*
* Controller used for URL /firestation?stationNumber=<station_number>
* 
* @param station
* Return children's number,adults' number and the list of people
* 			(firstName, lastName, address, phone)
* 			covered by this station
*/

@RestController
public class FireStationAlertController {
	
	static Logger log = LogManager.getLogger(FireStationAlertController.class.getName());
	
	private IFireStationAlertService fireStationAlertService;

	@Autowired
	public FireStationAlertController (IFireStationAlertService fireStationAlertService) {
		this.fireStationAlertService = fireStationAlertService;
	};

	
	@GetMapping("/firestation")
	public FireStationAlert getFireStationAlertData(@RequestParam String stationNumber) {
		log.info("GET request - endpoint /firestation - stationNumber = "+stationNumber);
	    return fireStationAlertService.getFireStationsAlert(stationNumber);
	}
	

}
