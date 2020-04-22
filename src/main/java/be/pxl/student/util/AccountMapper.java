package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class AccountMapper {
	private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);

	public Account map(String validLine) throws InvalidPaymentException
	{
		Account account = new Account();
		String[] values = validLine.split(",");
		if (values.length != 7) {
			throw new InvalidPaymentException("The payment wasn't valid");
		}
		account.setName(values[0]);
		account.setIBAN(values[1]);
		// Wed Feb 12 03:53:32 CET 2020
//		account.setPayments(new ArrayList<>());
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
//		Payment payment = new Payment(LocalDateTime.parse(values[3], formatter), Float.parseFloat(values[4]), values[5], values[6]);
//		account.getPayments().add(payment);

		return account;
	}
}
