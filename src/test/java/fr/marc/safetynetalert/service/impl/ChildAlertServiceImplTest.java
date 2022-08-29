package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.ChildAlert;

public class ChildAlertServiceImplTest {
	
	ChildAlertServiceImpl childAlertServiceImpl = new ChildAlertServiceImpl();
	
	@Test
	public void getChildAlertList_success () {
		
		// WHEN
		final ChildAlert resultList = childAlertServiceImpl.getChildAlertList("4 rue de Framboisy",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList.getChild())
			.filteredOn(c -> c.getFirstName().contains("Ric"));
		assertThat(resultList.getOtherMember())
			.filteredOn(c -> c.getFirstName().contains("Nery"))
			.filteredOn(c -> c.getFirstName().contains("Arthur"));
	}
	
	@Test
	public void getPhoneAlertList_no_child () {
		
		// WHEN
		final ChildAlert resultList = childAlertServiceImpl.getChildAlertList("9 rue de Framboisy",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList.getChild()).isNull();
		assertThat(resultList.getOtherMember()).isNull();
	}

	@Test
	public void getPhoneAlertList_no_answer () {
		
		// WHEN
		final ChildAlert resultList = childAlertServiceImpl.getChildAlertList("chemin de traverse",DBConstants.DATABASE_TEST);

		// THEN
		assertThat(resultList.getChild()).isNull();
		assertThat(resultList.getOtherMember()).isNull();
	}

}
