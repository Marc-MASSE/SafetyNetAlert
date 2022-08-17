package fr.marc.safetynetalert.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgeCalculator {
	
	public int getAge (String birthDateStringFormat, LocalDate currentDate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate birthDate = LocalDate.parse(birthDateStringFormat, formatter);
		int age = birthDate.until(currentDate).getYears();
		
		return age;
	}

}
