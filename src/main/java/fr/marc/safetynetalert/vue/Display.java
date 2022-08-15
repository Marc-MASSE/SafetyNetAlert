package fr.marc.safetynetalert.vue;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;

public class Display {

	public static void thisPerson(Person p) {

		System.out.println(p.getFirstName().concat(p.getLastName()).concat(p.getAddress()).concat(p.getCity()).concat(p.getPhone()).concat(p.getZip())
				.concat(p.getEmail()));
	}
	
	public static void thisFireStation(FireStation f) {

		System.out.println(f.getAddress().concat(f.getStation()));
	}
	
	public static void thisMedicalRecord(MedicalRecord m) {

		System.out.println(m.getFirstName().concat(m.getLastName()).concat(m.getBirthdate()).concat(m.getMedications().toString()).concat(m.getAllergies().toString()));
	}	
	
}
