package fr.marc.safetynetalert.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/*
* Class used to calculate the age of a person from his date of birth and the current date
* The birth date is given in String format 
*/

public class AgeCalculator {
	
	public int getAge (String birthDateStringFormat, LocalDate currentDate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate birthDate = LocalDate.parse(birthDateStringFormat, formatter);
		int age = birthDate.until(currentDate).getYears();
		
		return age;
	}

}
