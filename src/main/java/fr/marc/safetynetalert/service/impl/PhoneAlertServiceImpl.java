package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IPhoneAlertService;

/*
* Class used for URL /phoneAlert?firestation=<firestation_number>
* 
* @param firestation
* Return list of phone numbers of people covered by this fire station
*/

@Service
public class PhoneAlertServiceImpl implements IPhoneAlertService {
	
	private DataForRequest dataForRequest;
	
	@Autowired
	public PhoneAlertServiceImpl(DataForRequest dataForRequest) {
		this.dataForRequest = dataForRequest;
	}

	@Override
	public List<String> getPhoneAlertList(String firestation) {
		
		List<ConcatenatedFormat> matchingList = new ArrayList<>();
		List<String> phoneList = new ArrayList<>();
		
		matchingList = dataForRequest.getData()
				.stream()
				.filter(f-> f.getStationNumber() == null ? false : f.getStationNumber().toString().equals(firestation))
				.toList();
		
		matchingList.forEach(m -> {
			phoneList.add(m.getPhone());
		});
		
		return phoneList;
	}

}
