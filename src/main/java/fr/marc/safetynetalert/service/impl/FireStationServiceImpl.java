package fr.marc.safetynetalert.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.JsonData;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.service.IFireStationService;

@Service
@Component
public class FireStationServiceImpl implements IFireStationService {
	
	@Autowired
	JsonData jsonData;

	@Override
	public Optional<FireStation> getFireStationsByStation(String station) {
		
		Optional<FireStation> firestationList = jsonData.getFireStations().stream().filter(f->
		f.getStation().equals(station)).findAny();
		return Optional.ofNullable(firestationList.orElse(null));
	}

	@Override
	public FireStation getFireStationsByAddress(String address) {
		// TODO Auto-generated method stub
		return null;
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
	public Person saveFireStation(FireStation fireStation) {
		// TODO Auto-generated method stub
		return null;
	}

}
