package fr.marc.safetynetalert.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.service.IPhoneAlertService;

@RestController
public class PhoneAlertController {
	
	static Logger log = LogManager.getLogger(PhoneAlertController.class.getName());
	
	private IPhoneAlertService phoneAlertService;
    
    @Autowired
	public PhoneAlertController(IPhoneAlertService phoneAlertService) {
		this.phoneAlertService = phoneAlertService;
	}
	
	@GetMapping("/phoneAlert")
	public List<String> getPhoneAlertData (@RequestParam String firestation){
		log.info("GET request - endpoint /phoneAlert - stationNumber = "+firestation);
		return phoneAlertService.getPhoneAlertList(firestation);
	};

}
