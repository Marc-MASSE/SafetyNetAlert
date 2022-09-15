package fr.marc.safetynetalert.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.util.ToStringListConverter;

/*
 * Class used to read and collect data contained in data.json
 */

public class Retrieval {
	
	/*
	 * @return person's list contained in data.json
	 */
	public static List<Person> personsList() throws IOException {
		
	    byte[] bytesFile = Files.readAllBytes(new File(DBConstants.PATH_TO_JSON_FILE).toPath());
	    
    	JsonIterator iter = JsonIterator.parse(bytesFile);
    	Any any = iter.readAny();
    	
		Any personAny = any.get("persons");
		
		List<Person> persons = new ArrayList<>();

		personAny.forEach(a -> {
			Person person = new Person();
			person.setFirstName(a.get("firstName").toString());
			person.setLastName(a.get("lastName").toString());
			person.setAddress(a.get("address").toString());
			person.setCity(a.get("city").toString());
			person.setZip(a.get("zip").toString());
			person.setPhone(a.get("phone").toString());
			person.setEmail(a.get("email").toString());
			persons.add(person);
		});
		return persons;
	}

	/*
	 * @return fireStation's list contained in data.json
	 */
	public static List<FireStation> fireStationsList() throws IOException {
		
	    byte[] bytesFile = Files.readAllBytes(new File(DBConstants.PATH_TO_JSON_FILE).toPath());
	    
    	JsonIterator iter = JsonIterator.parse(bytesFile);
    	Any any = iter.readAny();
    	
		Any fireStationAny = any.get("firestations");
		
		List<FireStation> fireStations = new ArrayList<>();

		fireStationAny.forEach(a -> {
			FireStation fireStation = new FireStation();
			fireStation.setAddress(a.get("address").toString());
			fireStation.setStation(a.get("station").toString());
			fireStations.add(fireStation);
		});
		return fireStations;
	}

	/*
	 * @return medicalRecord's list contained in data.json
	 */
	public static List<MedicalRecord> medicalRecordsList() throws IOException {
		
	    byte[] bytesFile = Files.readAllBytes(new File(DBConstants.PATH_TO_JSON_FILE).toPath());
	    
    	JsonIterator iter = JsonIterator.parse(bytesFile);
    	Any any = iter.readAny();
    	
		Any medicalRecordAny = any.get("medicalrecords");
		
		List<MedicalRecord> medicalRecords = new ArrayList<>();

		medicalRecordAny.forEach(a -> {
			MedicalRecord medicalRecord = new MedicalRecord();
			medicalRecord.setFirstName(a.get("firstName").toString());
			medicalRecord.setLastName(a.get("lastName").toString());
			medicalRecord.setBirthdate(a.get("birthdate").toString());
			
			medicalRecord.setMedications(ToStringListConverter.convert(a.get("medications").asList()));
			medicalRecord.setAllergies(ToStringListConverter.convert(a.get("allergies").asList()));
			
			medicalRecords.add(medicalRecord);
		});
		return medicalRecords;
	}	
	
}
