package fr.marc.safetynetalert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.Retrieval;
import fr.marc.safetynetalert.service.impl.JsonDataService;
import fr.marc.safetynetalert.vue.Display;

@SpringBootApplication
public class SafetynetalertApplication {

public class SafetynetalertApplication {	
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(SafetynetalertApplication.class, args);
		
		/*
		 * Retrieval class retrieve persons, firestations and medicalrecords lists from data.json
		 * jsonData collects these lists to create the Data Base 
		 */
		
		/*
		JsonDataService jsonDataService;
		jsonDataService.initialization();
	
		
		

		System.out.println("-------------------------Liste des personnes---------------------------------------");

		
		List<Person> persons = Retrieval.personsList();
		
		persons.forEach(p -> Display.thisPerson(p));		

		System.out.println("--------------------------Recherche d'une personne-------------------------------");


		List<Person> personsFinded = new ArrayList<>();
		persons.forEach(p ->{
			if (p.getFirstName().equals("Brian")) {
				personsFinded.add(p);
			}
		});
		personsFinded.forEach(p -> Display.thisPerson(p));

		System.out.println("---------------------------Liste des casernes------------------------");		

		List<FireStation> fireStations = Retrieval.fireStationsList();
		
		fireStations.forEach(f -> Display.thisFireStation(f));
		
		
		System.out.println("---------------------------Liste des dossiers médicaux------------------------");		

		List<MedicalRecord> medicalRecords = Retrieval.medicalRecordsList();
		
		medicalRecords.forEach(m -> Display.thisMedicalRecord(m));
		
		System.out.println("---------------------------------------------------------------------------");
		
	}
	*/
	}

}
