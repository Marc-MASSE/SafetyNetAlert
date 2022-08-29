package fr.marc.safetynetalert.service;

import java.util.List;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.PersonInfo;

public interface IPersonInfoService {
	
	List<PersonInfo> getPersonInfoList(final String firstName, final String lastName, final List<ConcatenatedFormat> dataList);

}
