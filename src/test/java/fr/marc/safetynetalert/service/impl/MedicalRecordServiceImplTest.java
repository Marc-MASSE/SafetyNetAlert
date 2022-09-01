package fr.marc.safetynetalert.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.marc.safetynetalert.constants.DBConstants;
import fr.marc.safetynetalert.model.MedicalRecord;
import fr.marc.safetynetalert.model.Person;

public class MedicalRecordServiceImplTest {
	
	MedicalRecordServiceImpl medicalRecordServiceImpl = new MedicalRecordServiceImpl();
	
	@Test
	public void getMedicalRecords_success() {

		// WHEN
		final List<MedicalRecord> resultList = medicalRecordServiceImpl.getMedicalRecords(DBConstants.MEDICALRECORD_DATA_TEST);

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
			final MedicalRecord resultMedicalRecord = medicalRecordServiceImpl.getMedicalRecord("Marc","Sagar",DBConstants.MEDICALRECORD_DATA_TEST);

			// THEN
			assertThat(resultMedicalRecord.equals(DBConstants.medicalRecord1));
		}

		@Test
		public void getMedicalRecord_no_answer() {

			// WHEN
			final MedicalRecord resultMedicalRecord = medicalRecordServiceImpl.getMedicalRecord("Nemo","Personne",DBConstants.MEDICALRECORD_DATA_TEST);

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
			
			//GIVEN
			List<MedicalRecord> medicalrecordData = new ArrayList<>();
			medicalrecordData.addAll(DBConstants.MEDICALRECORD_DATA_TEST);

			// WHEN
			medicalRecordServiceImpl.deleteMedicalRecord("Marc","Sagar",medicalrecordData);

			// THEN
			assertThat(medicalrecordData).doesNotContain(DBConstants.medicalRecord1);
		}

		@Test
		public void deleteMedicalRecord_does_not_exist() {

			//GIVEN
			List<MedicalRecord> medicalrecordData = new ArrayList<>();
			medicalrecordData.addAll(DBConstants.MEDICALRECORD_DATA_TEST);

			// WHEN
			medicalRecordServiceImpl.deleteMedicalRecord("Nemo","Personne",medicalrecordData);

			// THEN
			assertThat(medicalrecordData)
				.filteredOn(m -> m.getFirstName().equals("Nemo"))
				.isEmpty();;
		}
	}

	@Test
	public void saveMedicalRecord_success() {
		
		//GIVEN
		List<MedicalRecord> medicalRecordData = new ArrayList<>();
		medicalRecordData.addAll(DBConstants.MEDICALRECORD_DATA_TEST);

		// WHEN
		medicalRecordServiceImpl.saveMedicalRecord(DBConstants.medicalRecordToAdd,medicalRecordData);

		// THEN
		assertThat(medicalRecordData).contains(DBConstants.medicalRecordToAdd);
	}
	
	@Nested
	class GetPersonAge {

		@Test
		public void getPersonAge_success() {
			
			// GIVEN
			LocalDate currentDate = LocalDate.of(2022, 07, 19);

			// WHEN
			final int age = medicalRecordServiceImpl.getPersonsAge("Ric","Bald",currentDate,DBConstants.MEDICALRECORD_DATA_TEST);

			// THEN
			assertThat(age).isEqualTo(12);
		}

		@Test
		public void getAge_for_person_doesnt_exist() {

			// GIVEN
			LocalDate currentDate = LocalDate.of(2022, 07, 19);

			// WHEN
			final int age = medicalRecordServiceImpl.getPersonsAge("Nemo","Personne",currentDate,DBConstants.MEDICALRECORD_DATA_TEST);

			// THEN
			assertThat(age).isEqualTo(-1);
		}
		
		@Test
		public void getAge_for_birthdate_doesnt_exist() {

			// GIVEN
			LocalDate currentDate = LocalDate.of(2022, 07, 19);
			List<MedicalRecord> dataList = new ArrayList<>();
			dataList.add(DBConstants.medicalRecordFalseDate);

			// WHEN
			final int age = medicalRecordServiceImpl.getPersonsAge("Adeline","Plus",currentDate,dataList);

			// THEN
			assertThat(age).isEqualTo(-1);
		}
		
	}

	@Nested
	class UpdateMedicalRecord {

		@Test
		public void updateMedicalRecord_success() {
			
			//GIVEN
			List<MedicalRecord> medicalRecordData = new ArrayList<>();
			medicalRecordData.addAll(DBConstants.MEDICALRECORD_DATA_TEST);
			
			// WHEN
			medicalRecordServiceImpl.updateMedicalRecord("Marc","Sagar",DBConstants.medicalRecordToAdd,medicalRecordData);

			// THEN
			// The entire medical record is updated except his firstName and lastName
			assertThat(medicalRecordData.get(0).getFirstName().equals("Marc"));
			assertThat(medicalRecordData.get(0).getLastName().equals("Sagar"));
			assertThat(medicalRecordData.get(0).getBirthdate().equals("05/17/1983"));
			assertThat(medicalRecordData.get(0).getMedications().equals(List.of("Camomille")));
			assertThat(medicalRecordData.get(0).getAllergies().equals(List.of("Tilleul")));
		}

		@Test
		public void updateMedicalRecord_does_not_exist() {

			//GIVEN
			List<MedicalRecord> medicalRecordData = new ArrayList<>();
			medicalRecordData.addAll(DBConstants.MEDICALRECORD_DATA_TEST);
			
			// WHEN
			MedicalRecord medicalRecordToUpdate = medicalRecordServiceImpl.updateMedicalRecord("Nemo","Personne",DBConstants.medicalRecordToAdd,medicalRecordData);

			// THEN
			assertThat(medicalRecordToUpdate).isNull();
		}
	}
	
}
