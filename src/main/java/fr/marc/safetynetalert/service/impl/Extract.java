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
			//Logger.error("data.json file not found");
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

}
