package fr.marc.safetynetalert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IPersonService;

@RestController
public class FireStationController {

	@Autowired
	IFireStationService fireStationService;

	/**
	 * Read - Get all persons
	 * 
	 * @return - An Iterable object of Person full filled
	 */
	@GetMapping("/firestations")
	public Iterable<FireStation> getFireStations() {

		return fireStationService.getFireStations();
	}

}
