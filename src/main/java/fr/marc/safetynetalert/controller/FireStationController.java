package fr.marc.safetynetalert.controller;

import java.util.List;

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

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.service.IFireStationService;

@RestController
public class FireStationController {
	
	static Logger log = LogManager.getLogger(FireStationController.class.getName());

	private IFireStationService fireStationService;
	
	@Autowired
	public FireStationController(IFireStationService fireStationService) {
		this.fireStationService = fireStationService;
	}

	@GetMapping("/firestations")
	public Iterable<FireStation> getFireStations() {
		log.info("GET request - endpoint /firestations - return the entire list of firestations");
		return fireStationService.getFireStations();
	}
	
    @DeleteMapping("/firestation")
    public void deleteFireStationByParam(@RequestParam String address, @RequestParam String stationNumber) {
		log.info("DELETE request - endpoint /firestation - address = "+address+" stationNumber = "+stationNumber);
        if (address.equals("") && stationNumber.equals("")) {
    		log.info("No fire station is deleted");
        }else if (address.equals("")) {
    		log.info("Delete every addresses with stationNumber = "+stationNumber);
         	fireStationService.deleteFireStationsByStation(stationNumber);
        }else {
    		log.info("Delete the address = "+address);
        	fireStationService.deleteFireStationsByAddress(address);
        }
    }
    
    @PostMapping(value = "/firestation")
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
		log.info("POST request - endpoint /firestation - body = "+fireStation);
    	return fireStationService.saveFireStation(fireStation);
    }
    
    @PutMapping(value = "/firestation")
    public FireStation updateFireStation(@RequestParam String address,@RequestBody String stationNumber) {
		log.info("PUT request - endpoint /firestation - address = "+address+" body = "+stationNumber);
    	return fireStationService.updateFireStation(address, stationNumber);
      
    }
    
}
