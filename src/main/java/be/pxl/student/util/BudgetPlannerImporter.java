package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.time.LocalDateTime;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
	private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
	private PathMatcher csvPathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");

	public void importCsv(Path path) {
		if (!csvPathMatcher.matches(path)) {
			LOGGER.error("Invalid file: .csv expected. Provided: {}", path);
			return;
		}

		if (!Files.exists(path)) {
			LOGGER.error("File {} does not exist", path);
			return;
		}

		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line = null;
			Payment payment;
			Account account;

			reader.readLine();

			while ((line = reader.readLine()) != null) {
				//payment = new Payment(LocalDateTime.);
				AccountMapper mapper = new AccountMapper();
				System.out.println(mapper.map(line).toString());
			}
		}
		catch (IOException ex) {
			LOGGER.fatal("Something went wrong while reading the file: {}", path);
		}
	}
}
