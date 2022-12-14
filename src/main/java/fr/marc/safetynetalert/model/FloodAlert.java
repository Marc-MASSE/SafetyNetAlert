package fr.marc.safetynetalert.model;

import java.util.List;

public class FloodAlert {
	
	private String stationNumber;
	private List<FloodAlertPerStation> floodAlertPerStation;
	
	
	public FloodAlert() {
	}

	
	public String getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}

	public List<FloodAlertPerStation> getFloodAlertPerStation() {
		return floodAlertPerStation;
	}

	public void setFloodAlertPerStation(List<FloodAlertPerStation> floodAlertPerStation) {
		this.floodAlertPerStation = floodAlertPerStation;
	}
	

}
