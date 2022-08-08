package fr.marc.safetynetalert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.JSONFileReader;
import fr.marc.safetynetalert.vue.Display;

@SpringBootApplication
public class SafetynetalertApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SafetynetalertApplication.class, args);

		// String filePath = DBConstants.PATH_TO_JSON_FILE;
		// byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());

		// JsonIterator iter = JsonIterator.parse(bytesFile);

		JsonIterator iter = null;
		try {
			iter = JsonIterator.parse(JSONFileReader.read(DBConstants.PATH_TO_JSON_FILE));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Any any = iter.readAny();
		Any personAny = any.get("persons");
		List<Person> persons = new ArrayList<>();

		personAny.forEach(a -> persons.add(new Person.PersonBuilder().firstName(a.get("firstName").toString())
				.address(a.get("address").toString()).city(a.get("city").toString())
				.lastName(a.get("lastName").toString()).phone(a.get("phone").toString()).zip(a.get("zip").toString())
				.email(a.get("email").toString()).build()));

		persons.forEach(p -> Display.thisPerson(p));

		System.err.println("---------------------------------------------------------------------------");


		List<Person> personsFinded = persons.stream().filter(p -> p.firstName == "Brian").collect(Collectors.toList());
		personsFinded.forEach(p -> Display.thisPerson(p));

		System.err.println("---------------------------------------------------------------------------");		
		
		persons.stream().filter(p -> p.firstName == "Brian").forEach(p -> Display.thisPerson(p));

		System.err.println("---------------------------------------------------------------------------");		
		
		/*
		 * for (Person person : persons) {
		 * 
		 * if (person.firstName == "Eric" && person.lastName == "Cadigan") {
		 * Display.thisPerson(person); break; } }
		 * System.out.println("Personne non trouvée");
		 */
	}

}
