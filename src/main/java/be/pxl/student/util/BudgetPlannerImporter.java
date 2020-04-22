package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
	private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
	private PathMatcher csvPathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");
	private AccountMapper accountMapper = new AccountMapper();
	private CounterAccountMapper counterAccountMapper = new CounterAccountMapper();
	private PaymentMapper paymentMapper = new PaymentMapper();
	private Map<String, Account> createdAccounts = new HashMap<>();
	private EntityManager entityManager;

	public BudgetPlannerImporter(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

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
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			String line = null;
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				try {
					Payment payment = paymentMapper.map(line);
					payment.setAccountId(getOrCreateAccount(accountMapper.map(line)));
					payment.setCounterAccountId(getOrCreateAccount(counterAccountMapper.map(line)));
					entityManager.persist(payment);
				}
				catch (InvalidPaymentException e) {
					LOGGER.error("Error while mapping line: {}", e.getMessage());
				}
			}
			tx.commit();
		}
		catch (IOException e) {
			LOGGER.fatal("An error occured while reading file: {}", path);
		}
	}

	private Account getOrCreateAccount(Account account) {
		if (createdAccounts.containsKey(account.getIBAN())) {
			return createdAccounts.get(account.getIBAN());
		}
		entityManager.persist(account);
		createdAccounts.put(account.getIBAN(), account);
		return account;
	}



	// CODE BEFORE CHANGE WITH SCREENSHOTS
//	public void importCsv(Path path) {
//		EntityManagerFactory entityManagerFactory = null;
//		EntityManager entityManager = null;
//
//		if (!csvPathMatcher.matches(path)) {
//			LOGGER.error("Invalid file: .csv expected. Provided: {}", path);
//			return;
//		}
//
//		if (!Files.exists(path)) {
//			LOGGER.error("File {} does not exist", path);
//			return;
//		}
//
//		try (BufferedReader reader = Files.newBufferedReader(path)) {
//			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
//			entityManager = entityManagerFactory.createEntityManager();
//			EntityTransaction tx = entityManager.getTransaction();
//			tx.begin();
//			String line = null;
//			Payment payment;
//
//			reader.readLine();
//			AccountMapper mapper = new AccountMapper();
//
//			while ((line = reader.readLine()) != null) {
//				//payment = new Payment(LocalDateTime.);
//				try {
//					Account account = mapper.map(line);
//					entityManager.persist(account);
//					//LOGGER.debug(mapper.map(line).toString());
//				} catch (InvalidPaymentException ex) {
//					LOGGER.error("Error while maping line: {}", ex.getMessage());
//				}
//			}
//			tx.commit();
//			LOGGER.debug("Added succesfully!");
//		}
//		catch (IOException ex) {
//			LOGGER.fatal("Something went wrong while reading the file: {}", path);
//		}
//		catch (Exception ex)
//		{
//			LOGGER.fatal("Something went wrong while parsing the account: {}", ex.getMessage());
//		}
//		finally {
//			if (entityManager != null) {
//				entityManager.close();
//			}
//			if (entityManagerFactory != null) {
//				entityManagerFactory.close();
//			}
//		}
//	}
}
