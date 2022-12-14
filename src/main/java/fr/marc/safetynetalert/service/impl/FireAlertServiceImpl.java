package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.FireAlert;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IFireAlertService;

/*
 * Class used for URL /fire?address=<address>
 * 
 * @param address
 * Return list of people (firstName, lastName, phone, age, medications, allergies)
 * 			who live at this address
 */

@Service
public class FireAlertServiceImpl implements IFireAlertService {
	

	private DataForRequest dataForRequest;
	
	@Autowired
	public FireAlertServiceImpl(DataForRequest dataForRequest) {
		this.dataForRequest = dataForRequest;
	}
	
	@Override
	public List<FireAlert> getFireAlertList(String address) {
		
		List<ConcatenatedFormat> matchingList = new ArrayList<>();
		List<FireAlert> fireAlertList = new ArrayList<>();
		
		matchingList = dataForRequest.getData()
				.stream()
				.filter(f-> f.getAddress().equals(address))
				.toList();
		
		matchingList.forEach(m -> {
			
			FireAlert fireAlert = new FireAlert();
			
			fireAlert.setFirstName(m.getFirstName());
			fireAlert.setLastName(m.getLastName());
			fireAlert.setPhone(m.getPhone());
			fireAlert.setAge(m.getAge());
			fireAlert.setMedications(m.getMedications());
			fireAlert.setAllergies(m.getAllergies());
			fireAlert.setStationNumber(m.getStationNumber());
			
			fireAlertList.add(fireAlert);
		});
		
		return fireAlertList;
	}

}
