package fr.marc.safetynetalert.service;

import java.util.List;

import fr.marc.safetynetalert.model.ChildAlert;
import fr.marc.safetynetalert.model.ConcatenatedFormat;

public interface IChildAlertService {
	
	ChildAlert getChildAlertList(final String address, final List<ConcatenatedFormat> dataList);

}
