package fr.marc.safetynetalert.model;

public class DataForFireStation {
	
	private int adultNumber;
	private int childNumber;
	private PersonDataForFireStation personDataForFireStation;
	
	
	public DataForFireStation() {
	}


	public DataForFireStation(int adultNumber, int childNumber, PersonDataForFireStation personDataForFireStation) {
		this.adultNumber = adultNumber;
		this.childNumber = childNumber;
		this.personDataForFireStation = personDataForFireStation;
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


	public PersonDataForFireStation getPersonDataForFireStation() {
		return personDataForFireStation;
	}


	public void setPersonDataForFireStation(PersonDataForFireStation personDataForFireStation) {
		this.personDataForFireStation = personDataForFireStation;
	}
	
	

}
