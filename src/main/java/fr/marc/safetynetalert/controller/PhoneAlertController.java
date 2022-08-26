package fr.marc.safetynetalert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IPhoneAlertService;

@RestController
public class PhoneAlertController {
	
	@Autowired
	IPhoneAlertService phoneAlertService;
	
	@Autowired
	private DataForRequest dataForRequest;
	
	@GetMapping("/phoneAlert")
	public List<String> getPhoneAlertData (@RequestParam String firestation){
		
		return phoneAlertService.getPhoneAlertList(firestation, dataForRequest.getData());
	};

}
