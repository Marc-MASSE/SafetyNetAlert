package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.FloodAlert;
import fr.marc.safetynetalert.model.FloodAlertPerStation;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IFloodAlertService;

/*
* Class used for URL /flood/stations?stations=<a list of station_number>
* 
* @param stationList
* Return, by station, the list of people (firstName, lastName, phone, age, medications, allergies)
* 			covered by these stations
*/

@Service
public class FloodAlertServiceImpl implements IFloodAlertService {
	
	private DataForRequest dataForRequest;
	
	@Autowired
	public FloodAlertServiceImpl(DataForRequest dataForRequest) {
		this.dataForRequest = dataForRequest;
	}

	@Override
	public List<FloodAlert> getFloodAlertList(List<String> stationList) {
		
		List<FloodAlert> floodAlertList = new ArrayList<>();
		
		stationList.forEach(s -> {
			
			FloodAlert floodAlert = new FloodAlert();
			floodAlert.setStationNumber(s);
			floodAlert.setFloodAlertPerStation(new ArrayList<FloodAlertPerStation>());
			
			floodAlertList.add(floodAlert);
			});
			 
		
		dataForRequest.getData().forEach(d -> {
			
			floodAlertList.forEach(f -> {
			
				if (f.getStationNumber().equals(d.getStationNumber())) {
			
					FloodAlertPerStation floodAlertPerStation = new FloodAlertPerStation();

					floodAlertPerStation.setAddress(d.getAddress());
					floodAlertPerStation.setFirstName(d.getFirstName());
					floodAlertPerStation.setLastName(d.getLastName());
					floodAlertPerStation.setPhone(d.getPhone());
					floodAlertPerStation.setAge(d.getAge());
					floodAlertPerStation.setMedications(d.getMedications());
					floodAlertPerStation.setAllergies(d.getAllergies());
					
					f.getFloodAlertPerStation().add(floodAlertPerStation);
				}
			});
			
		});
		return floodAlertList;
	}
}
