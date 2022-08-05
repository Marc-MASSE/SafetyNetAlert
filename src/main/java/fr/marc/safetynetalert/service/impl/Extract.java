package fr.marc.safetynetalert.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.JSONFileReader;

public class Extract {
	
	public static List<Person> listOfPersons() {
		
		JsonIterator iter = null;
		try {
			iter = JsonIterator.parse(JSONFileReader.read(DBConstants.PATH_TO_JSON_FILE));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Any any = null;
		try {
			any = iter.readAny();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Any personAny = any.get("persons");
		List<Person> persons = new ArrayList<>();

		personAny.forEach(a -> persons.add(new Person.PersonBuilder().firstName(a.get("firstName").toString())
				.address(a.get("address").toString()).city(a.get("city").toString())
				.lastName(a.get("lastName").toString()).phone(a.get("phone").toString()).zip(a.get("zip").toString())
				.email(a.get("email").toString()).build()));
		
		return persons;
		
	}

}
