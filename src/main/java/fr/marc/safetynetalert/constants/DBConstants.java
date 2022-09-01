package fr.marc.safetynetalert.constants;

import java.util.List;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;

public class DBConstants {
	
	public static final String PATH_TO_JSON_FILE = "src/main/resources/data.json";
	
	
	public static ConcatenatedFormat concat1 = new ConcatenatedFormat("Marc","Sagar","9 rue de Framboisy","12-34-56-78-90","sage@email.com","1",56,List.of("Doliprane : 1000mg"),List.of("Peniciline"));
	public static ConcatenatedFormat concat2 = new ConcatenatedFormat("Françoise","Secondigny","11 rue de Framboisy","12-34-56-78-97","fsec@email.com","1",21,List.of(""),List.of(""));
	public static ConcatenatedFormat concat3 = new ConcatenatedFormat("Mel","Mandinos","3 rue de Framboisy","12-34-56-78-30","barde@email.com","2",24,List.of("Aspégic : 500mg"),List.of("Shellfish"));
	public static ConcatenatedFormat concat4 = new ConcatenatedFormat("Ric","Bald","4 rue de Framboisy","12-34-56-78-45","ric@email.com","3",12,List.of(""),List.of(""));
	public static ConcatenatedFormat concat5 = new ConcatenatedFormat("Nery","Bald","4 rue de Framboisy","12-34-56-78-45","nery@email.com","3",25,List.of(""),List.of(""));
	public static ConcatenatedFormat concat6 = new ConcatenatedFormat("Arthur","Bald","4 rue de Framboisy","12-34-56-78-45","art@email.com","3",27,List.of(""),List.of(""));
	public static ConcatenatedFormat concat7 = new ConcatenatedFormat("Mel","Mandinos","7 rue de Framboisy","12-34-56-78-97","bardesenior@email.com","2",72,List.of("Doliprane : 500mg"),List.of("Retirement"));
	
	public static final List<ConcatenatedFormat> DATABASE_TEST = List.of(concat1,concat2,concat3,concat4,concat5,concat6,concat7);
	
	
	public static Person person1 = new Person("Marc","Sagar","9 rue de Framboisy","Limoges","87000","12-34-56-78-90","sage@email.com");
	public static Person person2 = new Person("Françoise","Secondigny","11 rue de Framboisy","Limoges","87000","12-34-56-78-97","fsec@email.com");
	public static Person person3 = new Person("Mel","Mandinos","3 rue de Framboisy","Limoges","87000","12-34-56-78-30","barde@email.com");
	public static Person person4 = new Person("Ric","Bald","4 rue de Framboisy","Limoges","87000","12-34-56-78-45","ric@email.com");
	public static Person person5 = new Person("Nery","Bald","4 rue de Framboisy","Limoges","87000","12-34-56-78-45","nery@email.com");
	public static Person person6 = new Person("Arthur","Bald","4 rue de Framboisy","Limoges","87000","12-34-56-78-45","art@email.com");
	public static Person person7 = new Person("Mel","Mandinos","7 rue de Framboisy","Limoges","87000","12-34-56-78-97","bardesenior@email.com");
	public static Person personToAdd = new Person("Adeline","Plus","1 rue de Chanteloup","Limoges","87000","12-34-56-78-00","adplus@email.com");
	
	public static final List<Person> PERSON_DATA_TEST = List.of(person1,person2,person3,person4,person5,person6,person7);
	
	public static FireStation fireStation1 = new FireStation("9 rue de Framboisy","1");
	public static FireStation fireStation2 = new FireStation("11 rue de Framboisy","1");
	public static FireStation fireStation3 = new FireStation("3 rue de Framboisy","2");
	public static FireStation fireStation4 = new FireStation("4 rue de Framboisy","3");
	public static FireStation fireStation5 = new FireStation("7 rue de Framboisy","2");
	public static FireStation fireStationToAdd = new FireStation("1 rue de Chanteloup","4");
	
	public static final List<FireStation> FIRESTATION_DATA_TEST = List.of(fireStation1,fireStation2,fireStation3,fireStation4,fireStation5);
	
	public static MedicalRecord medicalRecord1 = new MedicalRecord("Marc","Sagar","11/25/1966",List.of("Doliprane : 1000mg"),List.of("Peniciline"));
	public static MedicalRecord medicalRecord2 = new MedicalRecord("Françoise","Secondigny","09/12/2002",List.of(""),List.of(""));
	public static MedicalRecord medicalRecord3 = new MedicalRecord("Mel","Mandinos","03/03/1998",List.of("Aspégic : 500mg"),List.of("Shellfish"));
	public static MedicalRecord medicalRecord4 = new MedicalRecord("Ric","Bald","02/12/2010",List.of(""),List.of(""));
	public static MedicalRecord medicalRecord5 = new MedicalRecord("Nery","Bald","01/01/1997",List.of(""),List.of(""));
	public static MedicalRecord medicalRecord6 = new MedicalRecord("Arthur","Bald","26/04/1995",List.of(""),List.of(""));
	public static MedicalRecord medicalRecord7 = new MedicalRecord("Mel","Mandinos","06/24/1950",List.of("Doliprane : 500mg"),List.of("Retirement"));
	public static MedicalRecord medicalRecordToAdd = new MedicalRecord("Adeline","Plus","05/17/1983",List.of("Camomille"),List.of("Tilleul"));
	public static MedicalRecord medicalRecordFalseDate = new MedicalRecord("Adeline","Plus","nowhen",List.of("Camomille"),List.of("Tilleul"));
	
	public static final List<MedicalRecord> MEDICALRECORD_DATA_TEST = List.of(medicalRecord1,medicalRecord2,medicalRecord3,medicalRecord4,medicalRecord5,medicalRecord6,medicalRecord7);
	
}
