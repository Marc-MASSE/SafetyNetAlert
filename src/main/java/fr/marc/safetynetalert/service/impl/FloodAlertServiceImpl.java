package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.FloodAlert;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IFloodAlertService;

@Service
public class FloodAlertServiceImpl implements IFloodAlertService {
	
	
	@Autowired
	private DataForRequest dataForRequest;

	@Override
	public List<FloodAlert> getFloodAlertList(List<String> stationList) {
		// TODO Auto-generated method stub
		
		List<ConcatenatedFormat> matchingList = new ArrayList<>();
		List<FloodAlert> floodAlertList = new ArrayList<>();
		
		stationList.forEach(s -> {
			
			List<ConcatenatedFormat> matchingListPart = new ArrayList<>();
			
			matchingListPart = dataForRequest.getData()
				.stream()
				.filter(f->f.getStationNumber().equals(s))
				.toList();
			
			matchingList.addAll(matchingListPart);
			
			});
		
		matchingList.forEach(m -> {
		
			FloodAlert floodAlert = new FloodAlert();
			
			floodAlert.setStationNumber(m.getStationNumber());
			floodAlert.setAddress(m.getAddress());
			floodAlert.setFirstName(m.getFirstName());
			floodAlert.setLastName(m.getLastName());
			floodAlert.setPhone(m.getPhone());
			floodAlert.setAge(m.getAge());
			floodAlert.setMedications(m.getMedications());
			floodAlert.setAllergies(m.getAllergies());
			
			floodAlertList.add(floodAlert);
		});
		
		return floodAlertList;
	}
	

}
