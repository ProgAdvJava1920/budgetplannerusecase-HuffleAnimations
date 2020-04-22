package be.pxl.student.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private float amount;
    private String currency;
    private String detail;
    @ManyToOne
    private Account accountId;
    @ManyToOne
    private Account counterAccountId;
//    @ManyToOne
//    private Label labelId;

    public Payment() {
        // JPA Only
    }

    public Payment(LocalDateTime date, float amount, String currency, String detail) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.detail = detail;
    }

    public Payment(Account account, Account counterAccount, LocalDateTime date, float amount, String currency, String detail) {
        this(date, amount, currency, detail);
        accountId = account;
        counterAccountId = counterAccount;
    }

    public int getId()
    {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Account getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Account accountId)
    {
        this.accountId = accountId;
    }

    public Account getCounterAccountId()
    {
        return counterAccountId;
    }

    public void setCounterAccountId(Account counterAccountId)
    {
        this.counterAccountId = counterAccountId;
    }

//    public Label getLabelId()
//    {
//        return labelId;
//    }

    @Override
    public String toString()
    {
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
