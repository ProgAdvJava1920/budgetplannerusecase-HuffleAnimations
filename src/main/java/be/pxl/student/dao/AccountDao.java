package be.pxl.student.dao;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.rest.AccountsRest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDao {
	private EntityManager entityManager;
	private static final Logger LOGGER = LogManager.getLogger(AccountsRest.class);

	public AccountDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Payment> findByName(String name) {
		TypedQuery<Account> findByName = entityManager.createNamedQuery("findByName", Account.class);

		findByName.setParameter("name", name);
		Account account = findByName.getSingleResult();

		return account.getPayments();
	}
}
