package fr.marc.safetynetalert.service;

import java.util.List;

import fr.marc.safetynetalert.model.FireAlert;

public interface IFireAlertService {
	
	List<FireAlert> getFireAlertList(final String address);

}
