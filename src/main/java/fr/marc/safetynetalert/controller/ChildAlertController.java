package fr.marc.safetynetalert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.marc.safetynetalert.model.ChildAlert;
import fr.marc.safetynetalert.service.IChildAlertService;

@RestController
public class ChildAlertController {

	private IChildAlertService childAlertService;

	@Autowired
	public ChildAlertController (IChildAlertService childAlertService) {
		this.childAlertService = childAlertService;
	};

	@GetMapping("/childAlert")
	public ChildAlert getChildAlertData(@RequestParam String address) {

		return childAlertService.getChildAlertList(address);
	}

}
