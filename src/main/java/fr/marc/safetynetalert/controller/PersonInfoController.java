package fr.marc.safetynetalert.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.PersonInfo;
import fr.marc.safetynetalert.service.IPersonInfoService;

@RestController
public class PersonInfoController {
	
	static Logger log = LogManager.getLogger(PersonInfoController.class.getName());
	
	private IPersonInfoService personInfoService;

	@Autowired
	public PersonInfoController (IPersonInfoService personInfoService) {
		this.personInfoService = personInfoService;
	};
	
	
	@GetMapping("/personInfo")
	public List<PersonInfo> getPersonInfoData(@RequestParam String firstName, @RequestParam String lastName ){
		log.info("GET request - endpoint /personInfo - firstName = "+firstName+" lastName = "+lastName);
		return personInfoService.getPersonInfoList(firstName, lastName);
	}
	

}
