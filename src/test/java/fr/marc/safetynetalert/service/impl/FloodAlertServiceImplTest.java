package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.FloodAlert;

public class FloodAlertServiceImplTest {
	
	FloodAlertServiceImpl floodAlertServiceImpl = new FloodAlertServiceImpl();
	
	@Test
	public void getFloodAlertList_success () {
		
		// WHEN
		final List<FloodAlert> resultList = floodAlertServiceImpl.getFloodAlertList(List.of("1","2"),DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList.get(0).getStationNumber()).isEqualTo("1");
		assertThat(resultList.get(0).getFloodAlertPerStation())
			.filteredOn(f -> f.getFirstName().contains("Marc"))
			.filteredOn(f -> f.getFirstName().contains("Françoise"));
		assertThat(resultList.get(1).getStationNumber()).isEqualTo("2");
		assertThat(resultList.get(1).getFloodAlertPerStation())
			.filteredOn(f -> f.getFirstName().contains("Mel"));
	}
	
	@Test
	public void getFloodAlertList_no_answer () {
		
		// WHEN
		final List<FloodAlert> resultList = floodAlertServiceImpl.getFloodAlertList(List.of("5"),DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList.get(0).getStationNumber()).isEqualTo("5");
		assertThat(resultList.get(0).getFloodAlertPerStation()).isEmpty();
	}

	

}
