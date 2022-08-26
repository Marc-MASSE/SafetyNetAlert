package fr.marc.safetynetalert.service;

import java.util.List;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.repository.DataForRequest;

public interface IPhoneAlertService {
	
	List<String> getPhoneAlertList (final String firestation, final List<ConcatenatedFormat> dataList);

}
