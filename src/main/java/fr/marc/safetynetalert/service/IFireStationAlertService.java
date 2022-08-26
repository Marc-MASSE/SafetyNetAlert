package fr.marc.safetynetalert.service;

import java.util.List;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.FireStationAlert;

public interface IFireStationAlertService {
	
	FireStationAlert getFireStationsAlert(final String station, final List<ConcatenatedFormat> dataList);


}
