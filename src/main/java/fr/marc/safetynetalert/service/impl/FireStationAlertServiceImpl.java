package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.FireStationAlert;
import fr.marc.safetynetalert.model.FireStationAlertPerson;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IFireStationAlertService;

/*
* Class used for URL /firestation?stationNumber=<station_number>
* 
* @param station
* Return children's number,adults' number and the list of people
* 			(firstName, lastName, address, phone)
* 			covered by this station
*/

@Service
public class FireStationAlertServiceImpl implements IFireStationAlertService {

	private int childNumber;
	private int adultNumber;
	
	private DataForRequest dataForRequest;
	
	@Autowired
	public FireStationAlertServiceImpl(DataForRequest dataForRequest) {
		this.dataForRequest = dataForRequest;
	}

	@Override
	public FireStationAlert getFireStationsAlert(String station) {
		
		FireStationAlert fireStationAlert = new FireStationAlert();
		List<ConcatenatedFormat> matchingList = new ArrayList<>();
		List<FireStationAlertPerson> fireStationAlertPersonList = new ArrayList<>();
		fireStationAlert.setFireStationAlertPersonList(fireStationAlertPersonList);
		
		matchingList = dataForRequest.getData()
			.stream()
			.filter(f-> f.getStationNumber() == null ? false : f.getStationNumber().toString().equals(station))
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
