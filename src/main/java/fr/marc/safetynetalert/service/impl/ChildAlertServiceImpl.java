package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.Child;
import fr.marc.safetynetalert.model.ChildAlert;
import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IChildAlertService;

/*
 * Class used for URL /childAlert?address=<address>
 * 
 * @param address
 * Return list of children and adults (firstName, lastName, age)
 * 			 who live at this address
 * 			return nothing if there is no child
 */

@Service
public class ChildAlertServiceImpl implements IChildAlertService {

	private DataForRequest dataForRequest;
	
	static Logger log = LogManager.getLogger(ChildAlertServiceImpl.class.getName());
	
	@Autowired
	public ChildAlertServiceImpl(DataForRequest dataForRequest) {
		this.dataForRequest = dataForRequest;
	}
	
	@Override
	public ChildAlert getChildAlertList(String address) {
		
		List<ConcatenatedFormat> matchingList = new ArrayList<>();
		List<Child> childList = new ArrayList<>();
		List<Child> adultList = new ArrayList<>();
		ChildAlert childAlert = new ChildAlert();
		
		matchingList = dataForRequest.getData()
				.stream()
				.filter(f-> f.getAddress().equals(address))
				.toList();
		
		matchingList.forEach(m -> {
			
			Child child = new Child();
			Child adult = new Child();
			
			if (m.getAge()>18) {
				adult.setFirstName(m.getFirstName());
				adult.setLastName(m.getLastName());
				adult.setAge(m.getAge());
				adultList.add(adult);
			}else {
				child.setFirstName(m.getFirstName());
				child.setLastName(m.getLastName());
				child.setAge(m.getAge());
				childList.add(child);
			}

			// If there is no child at this address, this method return an empty list
			if (!childList.isEmpty()) {
				childAlert.setChild(childList);
				childAlert.setOtherMember(adultList);
			}else {
				log.warn("No child at this address");
			}
		});
		
		return childAlert;
	}

}
