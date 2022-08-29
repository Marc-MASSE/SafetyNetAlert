package fr.marc.safetynetalert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.PersonInfo;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IPersonInfoService;

@RestController
public class PersonInfoController {
	
	@Autowired
	IPersonInfoService personInfoService;
	
	@Autowired
	private DataForRequest dataForRequest;
	
	@GetMapping("/personInfo")
	public List<PersonInfo> getPersonInfoData(@RequestParam String firstName, @RequestParam String lastName ){
		
		return personInfoService.getPersonInfoList(firstName, lastName, dataForRequest.getData());
	}
	

}
