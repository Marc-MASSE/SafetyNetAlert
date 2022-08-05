package fr.marc.safetynetalert.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JSONFileReader {

	public static byte[] read(String pathToJSONFile) throws IOException {

		return Files.readAllBytes(new File(pathToJSONFile).toPath());

	}

}
