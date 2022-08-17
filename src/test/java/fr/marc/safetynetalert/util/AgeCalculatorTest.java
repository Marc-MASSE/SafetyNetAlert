package fr.marc.safetynetalert.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AgeCalculatorTest {
	
	private static AgeCalculator ageCalculator = new AgeCalculator();
	
	@Test
	public void CalculatedAge_Should_Return_36() {
		
		String birthDateStringFormat = "11/25/1966";
		LocalDate currentDate = LocalDate.of(2003, 07, 19);
		
		assertThat(ageCalculator.getAge(birthDateStringFormat, currentDate)).isEqualTo(36);
		
	}

}
