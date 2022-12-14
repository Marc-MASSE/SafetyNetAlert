package fr.marc.safetynetalert.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.ChildAlert;
import fr.marc.safetynetalert.service.IChildAlertService;

/*
 * Controller used for URL /childAlert?address=<address>
 * 
 * @param address
 * Return list of children and adults (firstName, lastName, age)
 * 			 who live at this address
 * 			return nothing if there is no child
 */

@RestController
public class ChildAlertController {
	
	static Logger log = LogManager.getLogger(ChildAlertController.class.getName());

	private IChildAlertService childAlertService;

	@Autowired
	public ChildAlertController (IChildAlertService childAlertService) {
		this.childAlertService = childAlertService;
	};

	@GetMapping("/childAlert")
	public ChildAlert getChildAlertData(@RequestParam String address) {
		log.info("GET request - endpoint /childAlert - address = "+address);
		return childAlertService.getChildAlertList(address);
	}

}
