package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.PersonInfo;
import fr.marc.safetynetalert.service.IPersonInfoService;

@Service
public class PersonInfoServiceImpl implements IPersonInfoService {

	@Override
	public List<PersonInfo> getPersonInfoList(String firstName, String lastName, List<ConcatenatedFormat> dataList) {
		
		List<ConcatenatedFormat> matchingList = new ArrayList<>();
		List<PersonInfo> personInfoList = new ArrayList<>();
		
		matchingList = dataList
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
