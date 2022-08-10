package fr.marc.safetynetalert.vue;

import fr.marc.safetynetalert.model.Person;

public class Display {

	public static void thisPerson(Person p) {

		System.out.println(p.getFirstName().concat(p.getLastName()).concat(p.getAddress()).concat(p.getCity()).concat(p.getPhone()).concat(p.getZip())
				.concat(p.getEmail()));
	}
}
