package fr.marc.safetynetalert.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireStationService;

/*
 * Class used for endpoint /firestation
 * 
 * For updating fireStation's data list
 */

@Service
public class FireStationServiceImpl implements IFireStationService {
	
	private JsonData jsonData;
	
	@Autowired
	public FireStationServiceImpl(JsonData jsonData) {
		this.jsonData = jsonData;
	}

	@Override
	public List<FireStation> getFireStationsByStation(String station) {
		
		List<FireStation> firestationList = jsonData.getFireStations()
				.stream()
				.filter(f-> f.getStation().equals(station))
				.toList();
		return firestationList;
	}

	@Override
	public FireStation getStationByAddress(String address) {
		
		Optional<FireStation> matchingStation = jsonData.getFireStations()
				.stream()
				.filter(f-> f.getAddress().equals(address))
				.findFirst();
		return matchingStation.orElse(new FireStation());
	}

	@Override
	public List<FireStation> getFireStations() {

		return jsonData.getFireStations();
	}

	@Override
	public void deleteFireStationsByStation(String station) {
		
		List<FireStation> fireStationToDelete = jsonData.getFireStations()
				.stream()
				.filter(f -> f.getStation().equals(station))
				.toList();
		
		
		fireStationToDelete.forEach(f -> {
			jsonData.getFireStations().remove(f);
			});
	}

	@Override
	public void deleteFireStationsByAddress(String address) {
		
		Optional<FireStation> matchingFireStation = jsonData.getFireStations()
				.stream()
				.filter(f -> f.getAddress().equals(address))
				.findFirst();
		if (matchingFireStation.isPresent()) {
			jsonData.getFireStations().remove(matchingFireStation.get());
		}
		
	}

	@Override
	public FireStation saveFireStation(FireStation fireStation) {
		
		jsonData.getFireStations().add(fireStation);
		
		return fireStation;
	}

	@Override
	public FireStation updateFireStation(String address, String stationNumber) {
		
		Optional<FireStation> matchingFireStation = jsonData.getFireStations()
				.stream()
				.filter(f -> f.getAddress().equals(address))
				.findFirst();
		if (matchingFireStation.isPresent()) {
			matchingFireStation.get().setStation(stationNumber);
			
			return matchingFireStation.get();
		}
		
		return null;
	}

}
