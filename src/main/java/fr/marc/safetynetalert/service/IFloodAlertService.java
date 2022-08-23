package fr.marc.safetynetalert.service;

import java.util.List;

import fr.marc.safetynetalert.model.FloodAlert;

public interface IFloodAlertService {
	
	
	List<FloodAlert> getFloodAlertList(final List<String> stationList);
	

}
