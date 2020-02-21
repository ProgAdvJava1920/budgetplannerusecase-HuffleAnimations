package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class AccountMapperTest {
	private String validLine = "Jos,BE69771770897312,BE25169011561250,Sun Feb 16 02:35:43 CET 2020,2904.33,EUR,Autem " +
			"consequatur dolores et reprehenderit modi ab.";
	private String inValidLine = "Jos,BE69771770897312,BE25169011561250Sun Feb 16 02:35:43 CET 2020,2904.33,EUR,Autem " +
			"consequatur dolores et reprehenderit modi ab.";
	private AccountMapper mapper = new AccountMapper();

	@Test
	public void aValidLineIsMappedToAnAccount() throws Exception
	{
		Account result = mapper.map(validLine);
		assertNotNull(result);
		assertEquals("Jos", result.getName());
		assertEquals("BE69771770897312", result.getIBAN());
		assertEquals(1, result.getPayments().size());
		Payment resultPayment = result.getPayments().get(0);
		assertEquals(LocalDateTime.of(2020, 2, 16, 2, 35, 43), resultPayment.getDate());
		assertEquals("EUR", resultPayment.getCurrency());
		assertEquals(2904.33, resultPayment.getAmount(), 0.001);
		assertEquals("Autem consequatur dolores et reprehenderit modi ab.", resultPayment.getDetail());
	}

	@Test
	public void anInvalidLineThrowsAnException() {
		assertThrows(Exception.class, () -> mapper.map(inValidLine));
	}
}
