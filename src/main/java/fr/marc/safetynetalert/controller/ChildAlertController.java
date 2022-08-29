package fr.marc.safetynetalert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.ChildAlert;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IChildAlertService;

@RestController
public class ChildAlertController {
	
	@Autowired
	IChildAlertService childAlertService;
	
	@Autowired
	private DataForRequest dataForRequest;
	
	   @GetMapping("/childAlert")
	    public ChildAlert getChildAlertData(@RequestParam String address) {
	     	
	        return childAlertService.getChildAlertList(address, dataForRequest.getData());
	    }

}
