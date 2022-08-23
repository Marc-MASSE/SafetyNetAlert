package fr.marc.safetynetalert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.marc.safetynetalert.model.Child;
import fr.marc.safetynetalert.model.ChildAlert;
import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.repository.DataForRequest;
import fr.marc.safetynetalert.service.IChildAlertService;

@Service
public class ChildAlertServiceImpl implements IChildAlertService {
	
	@Autowired
	private DataForRequest dataForRequest;

	@Override
	public ChildAlert getChildAlertList(String address) {
		// TODO Auto-generated method stub
		
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

			childAlert.setChild(childList);
			childAlert.setOtherMember(adultList);
		});
		
		return childAlert;
	}

}
