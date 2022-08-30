package fr.marc.safetynetalert.service;

import java.util.List;
import fr.marc.safetynetalert.model.FireStation;

public interface IFireStationService {
	
	List<FireStation> getFireStationsByStation(final String station, final List<FireStation> dataBase);
	
	FireStation getStationByAddress(final String address, final List<FireStation> dataBase);

	List<FireStation> getFireStations(final List<FireStation> dataBase);

	void deleteFireStationsByStation(final String station, final List<FireStation> dataBase);
	
	void deleteFireStationsByAddress(final String address, final List<FireStation> dataBase);

	FireStation saveFireStation(final FireStation fireStation, final List<FireStation> dataBase);
	
	FireStation updateFireStation(final String address, final String stationNumber, final List<FireStation> dataBase);

}
