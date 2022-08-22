package fr.marc.safetynetalert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.PersonInfo;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IPersonInfoService;

@Service
public class PersonInfoServiceImpl implements IPersonInfoService {
	
	@Autowired
	private DataForRequest dataForRequest;

	@Override
	public List<PersonInfo> getPersonInfoList(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
