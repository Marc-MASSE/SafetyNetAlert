package fr.marc.safetynetalert.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.Person;
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
	
    
    @DeleteMapping("/firestation")
    public void deleteFireStationByParam(@RequestParam String stationNumber, @RequestParam String address) {
     	
        if (address.isBlank() && stationNumber.isBlank()) {
        }else if (address.isBlank()) {
         	fireStationService.deleteFireStationsByStation(stationNumber);
        }else {
        	fireStationService.deleteFireStationsByAddress(address);
        }
    }
    
    @PostMapping(value = "/firestation")
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
    	return fireStationService.saveFireStation(fireStation);
    }
    
    @PutMapping(value = "/firestation")
    public FireStation updateFireStation(@RequestParam String address,@RequestBody FireStation fireStation) {
    	return fireStationService.updateFireStation(address, fireStation);
      
    }
    
}
