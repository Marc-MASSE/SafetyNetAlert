package fr.marc.safetynetalert.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IMedicalRecordService;

@Component
public class DataForRequest {
	
	@Autowired
	IFireStationService fireStationService;
	
	@Autowired
	IMedicalRecordService medicalRecordService;
	
	//@Autowired
	//JsonData jsonData;
	
	private JsonData jsonData;

	@Autowired
	public DataForRequest(JsonData jsonData) {
		this.jsonData = jsonData;
	}
	
	public DataForRequest() {
	}
	
	
	private LocalDate now = LocalDate.now();
	
	
	public List<ConcatenatedFormat> getData () {
		
		List<ConcatenatedFormat> dataList = new ArrayList<>();
		
		jsonData.getPersons().forEach(p -> {
			
			ConcatenatedFormat data = new ConcatenatedFormat();
			
			data.setFirstName(p.getFirstName());
			data.setLastName(p.getLastName());
			data.setAddress(p.getAddress());
			data.setPhone(p.getPhone());
			data.setEmail(p.getEmail());
			data.setStationNumber(fireStationService.getStationByAddress(p.getAddress()).getStation());
			data.setAge(medicalRecordService.getPersonsAge(p.getFirstName(), p.getLastName(), now));
			data.setMedications(medicalRecordService.getMedicalRecord(p.getFirstName(), p.getLastName()).getMedications());
			data.setAllergies(medicalRecordService.getMedicalRecord(p.getFirstName(), p.getLastName()).getAllergies());
			
			dataList.add(data);
		});
		
		return dataList;
	}
	
}
