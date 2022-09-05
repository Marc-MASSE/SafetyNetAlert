package fr.marc.safetynetalert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.service.IPhoneAlertService;

@RestController
public class PhoneAlertController {
	
	private IPhoneAlertService phoneAlertService;
    
    @Autowired
	public PhoneAlertController(IPhoneAlertService phoneAlertService) {
		this.phoneAlertService = phoneAlertService;
	}
	
	@GetMapping("/phoneAlert")
	public List<String> getPhoneAlertData (@RequestParam String firestation){
		
		return phoneAlertService.getPhoneAlertList(firestation);
	};

}
