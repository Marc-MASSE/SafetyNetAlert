package fr.marc.safetynetalert.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireStationService;

@Service
public class FireStationServiceImpl implements IFireStationService {
	
	@Autowired
	JsonData jsonData;

	@Override
	public List<FireStation> getFireStationsByStation(String station, List<FireStation> dataBase) {
		
		List<FireStation> firestationList = dataBase
				.stream()
				.filter(f-> f.getStation().equals(station))
				.toList();
		return firestationList;
	}

	@Override
	public FireStation getStationByAddress(String address, List<FireStation> dataBase) {
		
		Optional<FireStation> matchingStation = dataBase
				.stream()
				.filter(f-> f.getAddress().equals(address))
				.findFirst();
		return matchingStation.orElse(new FireStation());
	}

	@Override
	public List<FireStation> getFireStations(List<FireStation> dataBase) {

		return dataBase;
	}

	@Override
	public void deleteFireStationsByStation(String station, List<FireStation> dataBase) {
		
		List<FireStation> fireStationToDelete = dataBase
				.stream()
				.filter(f -> f.getStation().equals(station))
				.toList();
		
		
		fireStationToDelete.forEach(f -> {
			dataBase.remove(f);
			});
	}

	@Override
	public void deleteFireStationsByAddress(String address, List<FireStation> dataBase) {
		
		Optional<FireStation> matchingFireStation = dataBase
				.stream()
				.filter(f -> f.getAddress().equals(address))
				.findFirst();
		if (matchingFireStation.isPresent()) {
			dataBase.remove(matchingFireStation.get());
		}
		
	}

	@Override
	public FireStation saveFireStation(FireStation fireStation, List<FireStation> dataBase) {
		
		dataBase.add(fireStation);
		
		return fireStation;
	}

	@Override
	public FireStation updateFireStation(String address, String stationNumber, List<FireStation> dataBase) {
		
		Optional<FireStation> matchingFireStation = dataBase
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
