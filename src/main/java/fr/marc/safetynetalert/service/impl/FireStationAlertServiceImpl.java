package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.FireStationAlert;
import fr.marc.safetynetalert.model.FireStationAlertPerson;
import fr.marc.safetynetalert.service.IFireStationAlertService;

@Service
public class FireStationAlertServiceImpl implements IFireStationAlertService {
	
	//@Autowired
	//private DataForRequest dataForRequest;

	private int childNumber;
	private int adultNumber;

	/*
	 * @return a data list that contains FirstName, LastName, Address, Phone, 
	 * StationNumber (the station on which this person depends) and the person's Age
	 */
	@Override
	public FireStationAlert getFireStationsAlert(String station, List<ConcatenatedFormat> dataList) {
		
		FireStationAlert fireStationAlert = new FireStationAlert();
		List<ConcatenatedFormat> matchingList = new ArrayList<>();
		List<FireStationAlertPerson> fireStationAlertPersonList = new ArrayList<>();
		
		matchingList = dataList
			.stream()
			.filter(f-> f.getStationNumber().toString().equals(station))
			.toList();
		
		childNumber = 0;
		adultNumber = 0;
		
		matchingList.forEach(m -> {
			
			FireStationAlertPerson fireStationAlertPerson = new FireStationAlertPerson();
			
			fireStationAlertPerson.setFirstName(m.getFirstName());
			fireStationAlertPerson.setLastName(m.getLastName());
			fireStationAlertPerson.setAddress(m.getAddress());
			fireStationAlertPerson.setPhone(m.getPhone());
			fireStationAlertPersonList.add(fireStationAlertPerson);
			if (m.getAge()>18) {
				adultNumber++;
			}else {
				childNumber++;
			}
		});
		
		fireStationAlert.setFireStationAlertPersonList(fireStationAlertPersonList);
		fireStationAlert.setAdultNumber(adultNumber);
		fireStationAlert.setChildNumber(childNumber);
		
		return fireStationAlert;
	}

}