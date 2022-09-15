package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.PersonInfo;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IPersonInfoService;

/*
* Class used for URL /personInfo?firstName=<firstName>&lastName=<lastName>
* 
* @param firsName&lastName
* Return firstName, lastName, address, age, email, medications, allergies
* 			of this person or a list if there are other persons with the same
* 			firstName and lastName
*/


@Service
public class PersonInfoServiceImpl implements IPersonInfoService {
	
	private DataForRequest dataForRequest;
	
	@Autowired
	public PersonInfoServiceImpl(DataForRequest dataForRequest) {
		this.dataForRequest = dataForRequest;
	}

	@Override
	public List<PersonInfo> getPersonInfoList(String firstName, String lastName) {
		
		List<ConcatenatedFormat> matchingList = new ArrayList<>();
		List<PersonInfo> personInfoList = new ArrayList<>();
		
		matchingList = dataForRequest.getData()
				.stream()
				.filter(f-> f.getFirstName().equals(firstName) && f.getLastName().equals(lastName))
				.toList();
		
		matchingList.forEach(m -> {
			
			PersonInfo personInfo = new PersonInfo();
			
			personInfo.setFirstName(m.getFirstName());
			personInfo.setLastName(m.getLastName());
			personInfo.setAddress(m.getAddress());
			personInfo.setAge(m.getAge());
			personInfo.setEmail(m.getEmail());
			personInfo.setMedications(m.getMedications());
			personInfo.setAllergies(m.getAllergies());
			
			personInfoList.add(personInfo);
		});
		
		return personInfoList;
	}

}
