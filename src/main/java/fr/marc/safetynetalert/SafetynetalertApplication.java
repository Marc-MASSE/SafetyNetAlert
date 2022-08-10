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

		persons.forEach(p -> Display.thisPerson(p));

		System.err.println("---------------------------------------------------------------------------");


		List<Person> personsFinded = new ArrayList<>();
		persons.forEach(p ->{
			if (p.getFirstName().equals("Eric")) {
				personsFinded.add(p);
			}
		});
		personsFinded.forEach(p -> Display.thisPerson(p));

		System.err.println("---------------------------------------------------------------------------");		
		

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
