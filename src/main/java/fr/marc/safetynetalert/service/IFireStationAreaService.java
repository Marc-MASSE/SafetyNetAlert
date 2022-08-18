package fr.marc.safetynetalert.service;

import fr.marc.safetynetalert.model.DataForFireStation;

public interface IFireStationAreaService {
	
	DataForFireStation getDataForFireStation (final String stationNumber);
	
	

}
