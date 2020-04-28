package be.pxl.student.resource;

import java.time.LocalDateTime;

public class PaymentResource {
	private int id;
	private String counterAccount;
	private LocalDateTime date;
	private float amount;
	private String currency;
	private  String detail;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCounterAccount()
	{
		return counterAccount;
	}

	public void setCounterAccount(String counterAccount)
	{
		this.counterAccount = counterAccount;
	}

	public LocalDateTime getDate()
	{
		return date;
	}

	public void setDate(LocalDateTime date)
	{
		this.date = date;
	}

	public float getAmount()
	{
		return amount;
	}

	public void setAmount(float amount)
	{
		this.amount = amount;
	}

	public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}
}
