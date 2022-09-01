package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.ChildAlert;
import fr.marc.safetynetalert.repository.DataForRequest;

import fr.marc.safetynetalert.service.IChildAlertService;

public class ChildAlertServiceImplTest {
	
	private IChildAlertService childAlertService;
	private DataForRequest dataForRequest;
	
	@BeforeEach
	public void init() {
		dataForRequest = new DataForRequest();
		dataForRequest.getDataTest();
		childAlertService = new ChildAlertServiceImpl(dataForRequest);
	}	
	
	@Test
	public void getChildAlertList_success () {
		
		// WHEN
		final ChildAlert resultList = childAlertService.getChildAlertList("4 rue de Framboisy");

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
		final ChildAlert resultList = childAlertService.getChildAlertList("9 rue de Framboisy");

		// THEN
		assertThat(resultList.getChild()).isNull();
		assertThat(resultList.getOtherMember()).isNull();
	}

	@Test
	public void getPhoneAlertList_no_answer () {
		
		// WHEN
		final ChildAlert resultList = childAlertService.getChildAlertList("chemin de traverse");

		// THEN
		assertThat(resultList.getChild()).isNull();
		assertThat(resultList.getOtherMember()).isNull();
	}

}
