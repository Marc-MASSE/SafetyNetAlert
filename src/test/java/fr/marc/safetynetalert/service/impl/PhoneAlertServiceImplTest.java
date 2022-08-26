package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.marc.safetynetalert.model.ConcatenatedFormat;
import fr.marc.safetynetalert.repository.DataForRequest;

@ExtendWith(MockitoExtension.class)
public class PhoneAlertServiceImplTest {
	
	//@Mock
	//DataForRequest dataForRequest;
	
	PhoneAlertServiceImpl phoneAlertServiceImpl = new PhoneAlertServiceImpl();
	
	List<ConcatenatedFormat> dataBaseTest = new ArrayList<>();
	ConcatenatedFormat concat1 = new ConcatenatedFormat("Marc","Sagar","9 rue de Framboisy","12-34-56-78-90","sage@email.com","1",56,List.of("Doliprane : 1000mg"),List.of("Peniciline"));
	ConcatenatedFormat concat2 = new ConcatenatedFormat("Françoise","Secondigny","11 rue de Framboisy","12-34-56-78-97","fsec@email.com","1",21,List.of(""),List.of(""));
	ConcatenatedFormat concat3 = new ConcatenatedFormat("Mel","Mandinos","3 rue de Framboisy","12-34-56-78-30","barde@email.com","2",24,List.of("Aspégic : 500mg"),List.of("Shellfish"));
	
	@Test
	public void getPhoneAlertList_should_return () {
		
		dataBaseTest.add(concat1);
		dataBaseTest.add(concat2);
		dataBaseTest.add(concat3);

		// GIVEN
		//when(dataForRequest.getData()).thenReturn(dataBaseTest);

		// WHEN
		final List<String> resultList = phoneAlertServiceImpl.getPhoneAlertList("1",dataBaseTest);

		// THEN
		assertThat(resultList).isEqualTo(List.of("12-34-56-78-90","12-34-56-78-97"));
		
	}

}
