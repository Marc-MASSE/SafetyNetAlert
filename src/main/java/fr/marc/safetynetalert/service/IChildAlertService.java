package fr.marc.safetynetalert.service;

import fr.marc.safetynetalert.model.ChildAlert;

public interface IChildAlertService {
	
	ChildAlert getChildAlertList(final String address);

}
