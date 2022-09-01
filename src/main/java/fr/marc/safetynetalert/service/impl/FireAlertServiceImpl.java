package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.FireAlert;
import fr.marc.safetynetalert.service.IFireAlertService;

@Service
public class FireAlertServiceImpl implements IFireAlertService {
	
	@Override
	public List<FireAlert> getFireAlertList(String address, List<ConcatenatedFormat> dataList) {
		
		List<ConcatenatedFormat> matchingList = new ArrayList<>();
		List<FireAlert> fireAlertList = new ArrayList<>();
		
		matchingList = dataList
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
