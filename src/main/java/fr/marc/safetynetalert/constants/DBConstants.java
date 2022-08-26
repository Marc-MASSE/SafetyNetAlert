package fr.marc.safetynetalert.constants;

import java.util.List;

import fr.marc.safetynetalert.model.ConcatenatedFormat;

public class DBConstants {
	
	static ConcatenatedFormat concat1 = new ConcatenatedFormat("Marc","Sagar","9 rue de Framboisy","12-34-56-78-90","sage@email.com","1",56,List.of("Doliprane : 1000mg"),List.of("Peniciline"));
	static ConcatenatedFormat concat2 = new ConcatenatedFormat("Françoise","Secondigny","11 rue de Framboisy","12-34-56-78-97","fsec@email.com","1",21,List.of(""),List.of(""));
	static ConcatenatedFormat concat3 = new ConcatenatedFormat("Mel","Mandinos","3 rue de Framboisy","12-34-56-78-30","barde@email.com","2",24,List.of("Aspégic : 500mg"),List.of("Shellfish"));
	static ConcatenatedFormat concat4 = new ConcatenatedFormat("Ric","Bald","4 rue de Framboisy","12-34-56-78-45","ric@email.com","3",12,List.of(""),List.of(""));
	static ConcatenatedFormat concat5 = new ConcatenatedFormat("Nery","Bald","4 rue de Framboisy","12-34-56-78-45","nery@email.com","3",25,List.of(""),List.of(""));
	static ConcatenatedFormat concat6 = new ConcatenatedFormat("Arthur","Bald","4 rue de Framboisy","12-34-56-78-45","art@email.com","3",27,List.of(""),List.of(""));
	
	public static final List<ConcatenatedFormat> DATABASE_TEST = List.of(concat1,concat2,concat3,concat4,concat5,concat6);

	public static final String PATH_TO_JSON_FILE = "src/main/resources/data.json";
	
}
