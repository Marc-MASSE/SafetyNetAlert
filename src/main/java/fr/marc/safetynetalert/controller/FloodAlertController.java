package fr.marc.safetynetalert.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.FloodAlert;
import fr.marc.safetynetalert.service.IFloodAlertService;

/*
* Controller used for URL /flood/stations?stations=<a list of station_number>
* 
* @param stationList
* Return, by station, the list of people (firstName, lastName, phone, age, medications, allergies)
* 			covered by these stations
*/

@RestController
public class FloodAlertController {
	
	static Logger log = LogManager.getLogger(FloodAlertController.class.getName());
	
	private IFloodAlertService floodAlertService;

	@Autowired
	public FloodAlertController (IFloodAlertService floodAlertService) {
		this.floodAlertService = floodAlertService;
	};

	
	@GetMapping("/flood/stations")
	public List<FloodAlert> getFloodAlertData(@RequestParam List<String> stations){
		log.info("GET request - endpoint /flood/stations - stationNumber's list = "+stations);
		return floodAlertService.getFloodAlertList(stations);
		
	}

}
