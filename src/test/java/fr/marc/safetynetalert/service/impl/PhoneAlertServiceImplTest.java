package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import fr.marc.safetynetalert.constants.DBConstants;

public class PhoneAlertServiceImplTest {
	
	PhoneAlertServiceImpl phoneAlertServiceImpl = new PhoneAlertServiceImpl();
	
	@Test
	public void getPhoneAlertList_success () {
		
		// WHEN
		final List<String> resultList = phoneAlertServiceImpl.getPhoneAlertList("1",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList).isEqualTo(List.of("12-34-56-78-90","12-34-56-78-97"));
	}
	
	@Test
	public void getPhoneAlertList_no_answer () {
		
		// WHEN
		final List<String> resultList = phoneAlertServiceImpl.getPhoneAlertList("5",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList).isEmpty();
	}

}
