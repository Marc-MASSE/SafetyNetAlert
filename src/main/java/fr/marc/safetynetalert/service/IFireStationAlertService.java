package fr.marc.safetynetalert.service;

import fr.marc.safetynetalert.model.FireStationAlert;

public interface IFireStationAlertService {
	
	FireStationAlert getFireStationsAlert(final String station);


}
