package fr.marc.safetynetalert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.DataForFireStation;
import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.JsonData;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.model.PersonDataForFireStation;
import fr.marc.safetynetalert.service.IFireStationAreaService;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IPersonService;

@Service
@Component
public class FireStationAreaServiceImpl implements IFireStationAreaService {
	
	@Autowired
	JsonData jsonData;
	
	@Autowired
	IPersonService personService;
	
	@Autowired
	IFireStationService stationService;
	

	@Override
	public DataForFireStation getDataForFireStation(String stationNumber) {
		// TODO Auto-generated method stub
		DataForFireStation dataForFireStation = new DataForFireStation();
		PersonDataForFireStation personDataForFireStation = new PersonDataForFireStation();
		
		// List of addresses that match the station number
		List<FireStation> addressesInArea = stationService.getFireStationsByStation(stationNumber);
		
		personService.getPersons().forEach(p ->{
				for (FireStation fireStation : addressesInArea) {
					if (p.getAddress().equals(fireStation.getAddress())) {
						
					}
				}
		
		
			
		});
		
		
		
		return null;
	}
	

}
