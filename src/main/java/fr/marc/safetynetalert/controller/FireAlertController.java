package fr.marc.safetynetalert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.FireAlert;
import fr.marc.safetynetalert.service.IFireAlertService;

@RestController
public class FireAlertController {
	
	@Autowired
	IFireAlertService fireAlertService;
	
	@GetMapping
	public List<FireAlert> getFireAlertData (@RequestParam String address){
		
		return fireAlertService.getFireAlertList(address);
	}

}
