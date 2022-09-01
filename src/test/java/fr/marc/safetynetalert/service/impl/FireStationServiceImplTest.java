package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.FireStation;

public class FireStationServiceImplTest {
	
	FireStationServiceImpl fireStationServiceImpl = new FireStationServiceImpl();
	
	@Test
	public void getFireStations_success() {

		// WHEN
		final List<FireStation> resultList = fireStationServiceImpl.getFireStations(DBConstants.FIRESTATION_DATA_TEST);

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
			final List<FireStation> resultList = fireStationServiceImpl.getFireStationsByStation("2",DBConstants.FIRESTATION_DATA_TEST);

			// THEN
			assertThat(resultList.get(0).equals(DBConstants.fireStation3));
			assertThat(resultList.get(1).equals(DBConstants.fireStation5));
		}

		@Test
		public void getFireStationsByStation_no_answer() {

			// WHEN
			final List<FireStation> resultList = fireStationServiceImpl.getFireStationsByStation("5",DBConstants.FIRESTATION_DATA_TEST);

			// THEN
			assertThat(resultList).isEmpty();
		}
	}

	@Nested
	class GetFireStationByAddress {

		@Test
		public void getFireStationByAddress_success() {

			// WHEN
			final FireStation resultFireStation = fireStationServiceImpl.getStationByAddress("11 rue de Framboisy",DBConstants.FIRESTATION_DATA_TEST);

			// THEN
			assertThat(resultFireStation.equals(DBConstants.fireStation2));
		}

		@Test
		public void getFireStationByAddress_no_answer() {

			// WHEN
			final FireStation resultFireStation = fireStationServiceImpl.getStationByAddress("10 rue de Nulle Part",DBConstants.FIRESTATION_DATA_TEST);

			// THEN
			assertThat(resultFireStation.getAddress()).isNull();
			assertThat(resultFireStation.getStation()).isNull();
		}
	}

	@Nested
	class DeleteFireStationByStation {

		@Test
		public void deleteFireStationByStation_success() {
			
			//GIVEN
			List<FireStation> fireStationData = new ArrayList<>();
			fireStationData.addAll(DBConstants.FIRESTATION_DATA_TEST);

			// WHEN
			fireStationServiceImpl.deleteFireStationsByStation("1",fireStationData);

			// THEN
			assertThat(fireStationData.get(0).equals(DBConstants.fireStation3));
			assertThat(fireStationData.get(1).equals(DBConstants.fireStation4));
			assertThat(fireStationData.get(2).equals(DBConstants.fireStation5));
		}

		@Test
		public void deleteFireStationByStation_does_not_exist() {

			//GIVEN
			List<FireStation> fireStationData = new ArrayList<>();
			fireStationData.addAll(DBConstants.FIRESTATION_DATA_TEST);

			// WHEN
			fireStationServiceImpl.deleteFireStationsByStation("5",fireStationData);

			// THEN
			assertThat(fireStationData.get(0).equals(DBConstants.fireStation1));
			assertThat(fireStationData.get(1).equals(DBConstants.fireStation2));
			assertThat(fireStationData.get(2).equals(DBConstants.fireStation3));
			assertThat(fireStationData.get(3).equals(DBConstants.fireStation4));
			assertThat(fireStationData.get(4).equals(DBConstants.fireStation5));
		}
	}
	
	@Nested
	class DeleteFireStationByAddress {

		@Test
		public void deleteFireStationByAddress_success() {
			
			//GIVEN
			List<FireStation> fireStationData = new ArrayList<>();
			fireStationData.addAll(DBConstants.FIRESTATION_DATA_TEST);

			// WHEN
			fireStationServiceImpl.deleteFireStationsByAddress("11 rue de Framboisy",fireStationData);

			// THEN
			assertThat(fireStationData.get(0).equals(DBConstants.fireStation1));
			assertThat(fireStationData.get(1).equals(DBConstants.fireStation3));
		}

		@Test
		public void deleteFireStationByAddress_does_not_exist() {

			//GIVEN
			List<FireStation> fireStationData = new ArrayList<>();
			fireStationData.addAll(DBConstants.FIRESTATION_DATA_TEST);

			// WHEN
			fireStationServiceImpl.deleteFireStationsByAddress("10 rue de Nulle Part",fireStationData);

			// THEN
			assertThat(fireStationData.get(0).equals(DBConstants.fireStation1));
			assertThat(fireStationData.get(1).equals(DBConstants.fireStation2));
			assertThat(fireStationData.get(2).equals(DBConstants.fireStation3));
			assertThat(fireStationData.get(3).equals(DBConstants.fireStation4));
			assertThat(fireStationData.get(4).equals(DBConstants.fireStation5));
		}
	}
	
	@Test
	public void saveFireStation_success() {
		
		//GIVEN
		List<FireStation> fireStationData = new ArrayList<>();
		fireStationData.addAll(DBConstants.FIRESTATION_DATA_TEST);

		// WHEN
		fireStationServiceImpl.saveFireStation(DBConstants.fireStationToAdd,fireStationData);

		// THEN
		assertThat(fireStationData).contains(DBConstants.fireStationToAdd);
	}
	
	@Nested
	class UpdateFireStation {

		@Test
		public void updateFireStation_success() {
			
			//GIVEN
			List<FireStation> fireStationData = new ArrayList<>();
			fireStationData.addAll(DBConstants.FIRESTATION_DATA_TEST);
			
			// WHEN
			fireStationServiceImpl.updateFireStation("9 rue de Framboisy","4",fireStationData);

			// THEN
			// The station number is update for this address
			assertThat(fireStationData.get(0).getAddress().equals("9 rue de Framboisy"));
			assertThat(fireStationData.get(0).getStation().equals("4"));
		}

		@Test
		public void updateFireStation_does_not_exist() {

			//GIVEN
			List<FireStation> fireStationData = new ArrayList<>();
			fireStationData.addAll(DBConstants.FIRESTATION_DATA_TEST);

			// WHEN
			FireStation fireStationToUpdate = fireStationServiceImpl.updateFireStation("10 rue de Nulle Part","4",fireStationData);

			// THEN
			assertThat(fireStationToUpdate).isNull();
		}
	}
	
}
