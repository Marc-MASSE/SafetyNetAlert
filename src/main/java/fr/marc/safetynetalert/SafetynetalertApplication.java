package fr.marc.safetynetalert;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.marc.safetynetalert.service.impl.JsonDataService;

@SpringBootApplication
public class SafetynetalertApplication implements CommandLineRunner {

	@Autowired
	JsonDataService jsonDataService;
	
	static Logger log = LogManager.getLogger(SafetynetalertApplication.class.getName());
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(SafetynetalertApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		
		log.info("Initializing Database");
		jsonDataService.initialization();
	
	}

}
