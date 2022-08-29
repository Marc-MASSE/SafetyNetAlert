package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.FloodAlert;
import fr.marc.safetynetalert.model.FloodAlertPerStation;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IFloodAlertService;

@Service
public class FloodAlertServiceImpl implements IFloodAlertService {
	
	
	//@Autowired
	//private DataForRequest dataForRequest;

	@Override
	public List<FloodAlert> getFloodAlertList(List<String> stationList, List<ConcatenatedFormat> dataList) {
		// TODO Auto-generated method stub
		
		List<ConcatenatedFormat> matchingList = new ArrayList<>();
		List<FloodAlert> floodAlertList = new ArrayList<>();
		
		stationList.forEach(s -> {
			
			FloodAlert floodAlert = new FloodAlert();
			floodAlert.setStationNumber(s);
			floodAlert.setFloodAlertPerStation(new ArrayList<FloodAlertPerStation>());
			
			floodAlertList.add(floodAlert);
			
			
			//List<ConcatenatedFormat> matchingListPart = new ArrayList<>();
			
			//matchingListPart = dataList
			//	.stream()
			//	.filter(f->f.getStationNumber().equals(s))
			//	.toList();
			
			//matchingList.addAll(matchingListPart);
			
			});
			 
		
		dataList.forEach(d -> {
			
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
		

			
			//floodAlertList.add(floodAlert);
		
		return floodAlertList;
	}
	

}
