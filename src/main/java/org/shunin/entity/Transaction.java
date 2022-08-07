package org.shunin.entity;

import jdk.jfr.Timestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {


    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private Long transaction_id;

    @Column(name = "from_account_id")
    private String fromClient;

    @Column(name = "to_account_id")
    private String toClient;

    @Column(name = "rate")
    private double rate;

    @Column(name = "from_currency")
    @Enumerated(EnumType.STRING)
    private Currency fromCurrency;

    @Column(name = "to_currency")
    @Enumerated(EnumType.STRING)
    private Currency toCurrency;

    @Column(name = "amount")
    private double amount;

    @Column(name = "tranc_date")
    @Timestamp
    private Date transtactionDate = new Date();

    @ManyToOne()
    @JoinColumn(columnDefinition = "account_id")
    private Account account;

    public Transaction() {
    }

    public Transaction(String fromClient, String toClient, double rate, Currency fromCurrency,
                       Currency toCurrency, double amount,  Account account) {
        this.fromClient = fromClient;
        this.toClient = toClient;
        this.rate = rate;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.account = account;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {


        this.transaction_id = transaction_id;
    }


    private void makeTransaction() {

    }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




    public String getFromClient() {
        return fromClient;
    }

    public void setFromClient(String fromClient) {
        this.fromClient = fromClient;
    }

    public String getToClient() {
        return toClient;
    }

    public void setToClient(String toClient) {
        this.toClient = toClient;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(Currency toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTranstactionDate() {
        return transtactionDate;
    }

    public void setTranstactionDate(Date transtactionDate) {
        this.transtactionDate = transtactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
