package fr.marc.safetynetalert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.FloodAlert;
import fr.marc.safetynetalert.service.IFloodAlertService;

@RestController
public class FloodAlertController {
	
	@Autowired
	IFloodAlertService floodAlertService;
	
	@GetMapping("/flood/stations")
	public List<FloodAlert> getFloodAlertData(@RequestParam List<String> stations){
		
		return floodAlertService.getFloodAlertList(stations);
		
	}

}
