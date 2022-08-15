package fr.marc.safetynetalert.service;

import java.util.List;
import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.Person;

public interface IFireStationService {
	
	List<FireStation> getFireStationsByStation(final String station);
	
	FireStation getFireStationsByAddress(final String address);

	Iterable<FireStation> getFireStations();

	void deleteFireStationsByStation(final String station);
	
	void deleteFireStationsByAddress(final String address);

	Person saveFireStation(FireStation fireStation);

}
