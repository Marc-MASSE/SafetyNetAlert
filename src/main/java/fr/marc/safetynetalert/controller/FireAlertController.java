package fr.marc.safetynetalert.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.FireAlert;
import fr.marc.safetynetalert.service.IFireAlertService;

/*
 * Controller used for URL /fire?address=<address>
 * 
 * @param address
 * Return list of people (firstName, lastName, phone, age, medications, allergies)
 * 			who live at this address
 */

@RestController
public class FireAlertController {
	
	static Logger log = LogManager.getLogger(FireAlertController.class.getName());
	
	private IFireAlertService fireAlertService;

	@Autowired
	public FireAlertController (IFireAlertService fireAlertService) {
		this.fireAlertService = fireAlertService;
	};
	
	
	@GetMapping("/fire")
	public List<FireAlert> getFireAlertData (@RequestParam String address){
		log.info("GET request - endpoint /fire - address = "+address);
		return fireAlertService.getFireAlertList(address);
	}

}
