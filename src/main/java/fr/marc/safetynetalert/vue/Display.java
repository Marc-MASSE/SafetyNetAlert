package fr.marc.safetynetalert.vue;

import fr.marc.safetynetalert.model.Person;

public class Display {

	public static void thisPerson(Person p) {

		System.out.println(p.firstName.concat(p.lastName).concat(p.address).concat(p.city).concat(p.phone).concat(p.zip)
				.concat(p.email));
	}
}
