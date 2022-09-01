package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireStationService;

public class FireStationServiceImplTest {
	
	private IFireStationService fireStationService;
	private JsonData jsonData;
	
	
	@BeforeEach
	public void init() {
		jsonData = new JsonData();
		jsonData.setFireStations(new ArrayList<FireStation>());
		jsonData.getFireStations().addAll(DBConstants.FIRESTATION_DATA_TEST);
		fireStationService = new FireStationServiceImpl(jsonData);
	}
	
	@Test
	public void getFireStations_success() {

		// WHEN
		final List<FireStation> resultList = fireStationService.getFireStations();

		// THEN
		assertThat(resultList.get(0).equals(DBConstants.fireStation1));
		assertThat(resultList.get(2).equals(DBConstants.fireStation3));
		assertThat(resultList.get(4).equals(DBConstants.fireStation5));
	}

	@Nested
	class GetFireStationsByStation {

		@Test
		public void getFireStationsByStation_success() {

			// WHEN
			final List<FireStation> resultList = fireStationService.getFireStationsByStation("2");

			// THEN
			assertThat(resultList.get(0).equals(DBConstants.fireStation3));
			assertThat(resultList.get(1).equals(DBConstants.fireStation5));
		}

		@Test
		public void getFireStationsByStation_no_answer() {

			// WHEN
			final List<FireStation> resultList = fireStationService.getFireStationsByStation("5");

			// THEN
			assertThat(resultList).isEmpty();
		}
	}

	@Nested
	class GetFireStationByAddress {

		@Test
		public void getFireStationByAddress_success() {

			// WHEN
			final FireStation resultFireStation = fireStationService.getStationByAddress("11 rue de Framboisy");

			// THEN
			assertThat(resultFireStation.equals(DBConstants.fireStation2));
		}

		@Test
		public void getFireStationByAddress_no_answer() {

			// WHEN
			final FireStation resultFireStation = fireStationService.getStationByAddress("10 rue de Nulle Part");

			// THEN
			assertThat(resultFireStation.getAddress()).isNull();
			assertThat(resultFireStation.getStation()).isNull();
		}
	}

	@Nested
	class DeleteFireStationByStation {

		@Test
		public void deleteFireStationByStation_success() {
			
			// WHEN
			fireStationService.deleteFireStationsByStation("1");

			// THEN
			assertThat(jsonData.getFireStations().get(0).equals(DBConstants.fireStation3));
			assertThat(jsonData.getFireStations().get(1).equals(DBConstants.fireStation4));
			assertThat(jsonData.getFireStations().get(2).equals(DBConstants.fireStation5));
		}

		@Test
		public void deleteFireStationByStation_does_not_exist() {

			// WHEN
			fireStationService.deleteFireStationsByStation("5");

			// THEN
			assertThat(jsonData.getFireStations().get(0).equals(DBConstants.fireStation1));
			assertThat(jsonData.getFireStations().get(1).equals(DBConstants.fireStation2));
			assertThat(jsonData.getFireStations().get(2).equals(DBConstants.fireStation3));
			assertThat(jsonData.getFireStations().get(3).equals(DBConstants.fireStation4));
			assertThat(jsonData.getFireStations().get(4).equals(DBConstants.fireStation5));
		}
	}
	
	@Nested
	class DeleteFireStationByAddress {

		@Test
		public void deleteFireStationByAddress_success() {

			// WHEN
			fireStationService.deleteFireStationsByAddress("11 rue de Framboisy");

			// THEN
			assertThat(jsonData.getFireStations().get(0).equals(DBConstants.fireStation1));
			assertThat(jsonData.getFireStations().get(1).equals(DBConstants.fireStation3));
		}

		@Test
		public void deleteFireStationByAddress_does_not_exist() {

			// WHEN
			fireStationService.deleteFireStationsByAddress("10 rue de Nulle Part");

			// THEN
			assertThat(jsonData.getFireStations().get(0).equals(DBConstants.fireStation1));
			assertThat(jsonData.getFireStations().get(1).equals(DBConstants.fireStation2));
			assertThat(jsonData.getFireStations().get(2).equals(DBConstants.fireStation3));
			assertThat(jsonData.getFireStations().get(3).equals(DBConstants.fireStation4));
			assertThat(jsonData.getFireStations().get(4).equals(DBConstants.fireStation5));
		}
	}
	
	@Test
	public void saveFireStation_success() {

		// WHEN
		fireStationService.saveFireStation(DBConstants.fireStationToAdd);

		// THEN
		assertThat(jsonData.getFireStations()).contains(DBConstants.fireStationToAdd);
	}
	
	@Nested
	class UpdateFireStation {

		@Test
		public void updateFireStation_success() {
			
			// WHEN
			fireStationService.updateFireStation("9 rue de Framboisy","4");

			// THEN
			// The station number is update for this address
			assertThat(jsonData.getFireStations().get(0).getAddress().equals("9 rue de Framboisy"));
			assertThat(jsonData.getFireStations().get(0).getStation().equals("4"));
		}

		@Test
		public void updateFireStation_does_not_exist() {

			//GIVEN
			List<FireStation> fireStationData = new ArrayList<>();
			fireStationData.addAll(DBConstants.FIRESTATION_DATA_TEST);

			// WHEN
			FireStation fireStationToUpdate = fireStationService.updateFireStation("10 rue de Nulle Part","4");

			// THEN
			assertThat(fireStationToUpdate).isNull();
		}
	}
	
}
