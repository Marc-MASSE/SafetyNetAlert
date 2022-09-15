package fr.marc.safetynetalert.constants;

import java.util.Arrays;
import java.util.List;

import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;

/* 
 * Data base for service classes' unit test
 */

public class DBTest {
	
	public static List<Person> getPersonList(){
		return Arrays.asList(new Person("Marc","Sagar","9 rue de Framboisy","Limoges","87000","12-34-56-78-90","sage@email.com"),
				new Person("Françoise","Secondigny","11 rue de Framboisy","Limoges","87000","12-34-56-78-97","fsec@email.com"),
				new Person("Mel","Mandinos","3 rue de Framboisy","Limoges","87000","12-34-56-78-30","barde@email.com"),
				new Person("Ric","Bald","4 rue de Framboisy","Limoges","87000","12-34-56-78-45","ric@email.com"),
				new Person("Nery","Bald","4 rue de Framboisy","Limoges","87000","12-34-56-78-45","nery@email.com"),
				new Person("Arthur","Bald","4 rue de Framboisy","Limoges","87000","12-34-56-78-45","art@email.com"),
				new Person("Mel","Mandinos","7 rue de Framboisy","Limoges","87000","12-34-56-78-98","bardesenior@email.com"));
	}
	
	public static List<FireStation> getFireStationList() {
		return Arrays.asList(new FireStation("9 rue de Framboisy", "1"),
				new FireStation("11 rue de Framboisy", "1"),
				new FireStation("3 rue de Framboisy", "2"),
				new FireStation("4 rue de Framboisy", "3"),
				new FireStation("7 rue de Framboisy", "2"));
	}

	public static List<MedicalRecord> getMedicalRecordList() {
		return Arrays.asList(new MedicalRecord("Marc","Sagar","11/25/1966",List.of("Doliprane : 1000mg"),List.of("Peniciline")),
				new MedicalRecord("Françoise","Secondigny","09/12/2002",List.of(""),List.of("")),
				new MedicalRecord("Mel","Mandinos","03/03/1998",List.of("Aspégic : 500mg"),List.of("Shellfish")),
				new MedicalRecord("Ric","Bald","02/12/2010",List.of(""),List.of("")),
				new MedicalRecord("Nery","Bald","01/01/1997",List.of(""),List.of("")),
				new MedicalRecord("Arthur","Bald","04/26/1995",List.of(""),List.of("")),
				new MedicalRecord("Mel","Mandinos","06/24/1950",List.of("Doliprane : 500mg"),List.of("Retirement")));
	}
	
}
