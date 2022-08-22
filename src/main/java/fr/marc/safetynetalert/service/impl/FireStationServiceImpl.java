package fr.marc.safetynetalert.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireStationService;

@Service
public class FireStationServiceImpl implements IFireStationService {
	
	@Autowired
	JsonData jsonData;

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
		// TODO Auto-generated method stub
		Optional<FireStation> matchingStation = jsonData.getFireStations()
				.stream()
				.filter(f-> f.getAddress().equals(address))
				.findFirst();
		return matchingStation.orElse(null);
	}

	@Override
	public Iterable<FireStation> getFireStations() {

		return jsonData.getFireStations();
	}

	@Override
	public void deleteFireStationsByStation(String station) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFireStationsByAddress(String address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FireStation saveFireStation(FireStation fireStation) {
		// TODO Auto-generated method stub
		
		return fireStation;
	}

}
