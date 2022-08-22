package fr.marc.safetynetalert.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.service.IFireStationService;

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
	
	
    @GetMapping("/firestationByStation")
    public List<FireStation> getFireStationsByStationParam(@RequestParam String station) {
     	
        return fireStationService.getFireStationsByStation(station);
    }
	

}
