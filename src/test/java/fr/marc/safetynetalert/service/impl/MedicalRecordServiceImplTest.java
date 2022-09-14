package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.FireStation;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;
import fr.marc.safetynetalert.repository.JsonData;
import fr.marc.safetynetalert.service.IFireStationService;
import fr.marc.safetynetalert.service.IMedicalRecordService;

public class MedicalRecordServiceImplTest {
	
	private IMedicalRecordService medicalRecordService;
	private JsonData jsonData;
	
	@BeforeEach
	public void init() {
		jsonData = new JsonData();
		jsonData.setMedicalRecords(new ArrayList<MedicalRecord>());
		jsonData.getMedicalRecords().addAll(DBConstants.MEDICALRECORD_DATA_TEST);
		medicalRecordService = new MedicalRecordServiceImpl(jsonData);
	}
	
	@Test
	public void getMedicalRecords_success() {

		// WHEN
		final List<MedicalRecord> resultList = medicalRecordService.getMedicalRecords();

		// THEN
		assertThat(resultList.get(0).equals(DBConstants.medicalRecord1));
		assertThat(resultList.get(4).equals(DBConstants.medicalRecord5));
		assertThat(resultList.get(6).equals(DBConstants.medicalRecord7));
	}
	
	@Nested
	class GetMedicalRecord {

		@Test
		public void getMedicalRecord_success() {

			// WHEN
			final MedicalRecord resultMedicalRecord = medicalRecordService.getMedicalRecord("Marc","Sagar");

			// THEN
			assertThat(resultMedicalRecord.equals(DBConstants.medicalRecord1));
		}

		@Test
		public void getMedicalRecord_no_answer() {

			// WHEN
			final MedicalRecord resultMedicalRecord = medicalRecordService.getMedicalRecord("Nemo","Personne");

			// THEN
			assertThat(resultMedicalRecord.getFirstName()).isNull();
			assertThat(resultMedicalRecord.getLastName()).isNull();
			assertThat(resultMedicalRecord.getBirthdate()).isNull();
			assertThat(resultMedicalRecord.getMedications()).isNull();
			assertThat(resultMedicalRecord.getAllergies()).isNull();
		}
	}
	
	@Nested
	class DeleteMedicalRecord {

		@Test
		public void deleteMedicalRecord_success() {

			// WHEN
			medicalRecordService.deleteMedicalRecord("Marc","Sagar");

			// THEN
			assertThat(jsonData.getMedicalRecords()).doesNotContain(DBConstants.medicalRecord1);
		}

		@Test
		public void deleteMedicalRecord_does_not_exist() {

			// WHEN
			medicalRecordService.deleteMedicalRecord("Nemo","Personne");

			// THEN
			assertThat(jsonData.getMedicalRecords())
				.filteredOn(m -> m.getFirstName().equals("Nemo"))
				.isEmpty();;
		}
	}

	@Test
	public void saveMedicalRecord_success() {

		// WHEN
		medicalRecordService.saveMedicalRecord(DBConstants.medicalRecordToAdd);

		// THEN
		assertThat(jsonData.getMedicalRecords()).contains(DBConstants.medicalRecordToAdd);
	}
	
	@Nested
	class GetPersonAge {

		@Test
		public void getPersonAge_success() {
			
			// GIVEN
			LocalDate currentDate = LocalDate.of(2022, 07, 19);

			// WHEN
			final int age = medicalRecordService.getPersonsAge("Ric","Bald",currentDate);

			// THEN
			assertThat(age).isEqualTo(12);
		}

		@Test
		public void getAge_for_person_doesnt_exist() {

			// GIVEN
			LocalDate currentDate = LocalDate.of(2022, 07, 19);

			// WHEN
			final int age = medicalRecordService.getPersonsAge("Nemo","Personne",currentDate);

			// THEN
			assertThat(age).isEqualTo(-1);
		}
		
		@Test
		public void getAge_for_birthdate_doesnt_exist() {

			// GIVEN
			LocalDate currentDate = LocalDate.of(2022, 07, 19);
			jsonData.getMedicalRecords().add(DBConstants.medicalRecordFalseDate);

			// WHEN
			final int age = medicalRecordService.getPersonsAge("Adeline","Plus",currentDate);

			// THEN
			assertThat(age).isEqualTo(-1);
		}
		
	}

	@Nested
	class UpdateMedicalRecord {

		@Test
		public void updateMedicalRecord_success() {
			
			// WHEN
			medicalRecordService.updateMedicalRecord("Marc","Sagar",DBConstants.medicalRecordToAdd);

			// THEN
			// The entire medical record is updated except his firstName and lastName
			assertThat(jsonData.getMedicalRecords().get(0).getFirstName().equals("Marc"));
			assertThat(jsonData.getMedicalRecords().get(0).getLastName().equals("Sagar"));
			assertThat(jsonData.getMedicalRecords().get(0).getBirthdate().equals("05/17/1983"));
			assertThat(jsonData.getMedicalRecords().get(0).getMedications().equals(List.of("Camomille")));
			assertThat(jsonData.getMedicalRecords().get(0).getAllergies().equals(List.of("Tilleul")));
		}

		@Test
		public void updateMedicalRecord_does_not_exist() {
			
			// WHEN
			MedicalRecord medicalRecordToUpdate = medicalRecordService.updateMedicalRecord("Nemo","Personne",DBConstants.medicalRecordToAdd);

			// THEN
			assertThat(medicalRecordToUpdate).isNull();
		}
	}
	
}
