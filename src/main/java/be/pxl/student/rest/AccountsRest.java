package be.pxl.student.rest;

import be.pxl.student.dao.AccountDao;
import be.pxl.student.dao.EntityManagerUtil;
import be.pxl.student.entity.Payment;
import be.pxl.student.resource.PaymentResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/accounts")
public class AccountsRest {
	private static final Logger LOGGER = LogManager.getLogger(AccountsRest.class);

	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPaymentsByName(@PathParam("name") String accountName) {
		EntityManager em = EntityManagerUtil.createEntityManager();
		List<Payment> payments;
		try {
			AccountDao accountDao = new AccountDao(em);

			payments = accountDao.findByName(accountName);
			return Response.ok(mapPayments(payments)).build();
		} catch (NoResultException ex) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} finally {
			em.close();
		}
	}

	private List<PaymentResource> mapPayments(List<Payment> payments) {
		return payments.stream().map(p -> mapPayments(p)).collect(Collectors.toList());
	}

	private PaymentResource mapPayments(Payment payment) {
		PaymentResource result = new PaymentResource();
		result.setId(payment.getId());
		result.setAmount(payment.getAmount());
		result.setCounterAccount(payment.getCounterAccountId().getIBAN());
		result.setCurrency(payment.getCurrency());
		result.setDetail(payment.getDetail());
		return result;
	}
}
