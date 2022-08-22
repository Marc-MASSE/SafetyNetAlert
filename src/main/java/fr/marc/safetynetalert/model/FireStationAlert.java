package fr.marc.safetynetalert.model;

import java.util.List;

public class FireStationAlert {

	private int adultNumber;
	private int childNumber;
	private List<FireStationAlertPerson> FireStationAlertPersonList;

	public FireStationAlert() {
	}

	public FireStationAlert(int adultNumber, int childNumber,
			List<FireStationAlertPerson> personDataForFireStationList) {

		this.adultNumber = adultNumber;
		this.childNumber = childNumber;
		this.FireStationAlertPersonList = personDataForFireStationList;
	}

	public int getAdultNumber() {
		return adultNumber;
	}

	public void setAdultNumber(int adultNumber) {
		this.adultNumber = adultNumber;
	}

	public int getChildNumber() {
		return childNumber;
	}

	public void setChildNumber(int childNumber) {
		this.childNumber = childNumber;
	}

	public List<FireStationAlertPerson> getFireStationAlertPersonList() {
		return FireStationAlertPersonList;
	}

	public void setFireStationAlertPersonList(List<FireStationAlertPerson> personDataForFireStationList) {
		this.FireStationAlertPersonList = personDataForFireStationList;
	}

}
