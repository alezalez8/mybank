package org.shunin.entity;

import jdk.jfr.Timestamp;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "number_account", nullable = false)
    private String numberOfAccount;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "createDate")
    @Timestamp
    private Date createDate = new Date();


    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
    private Set<Transaction> transactionSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Account() {
    }

    public Account(String numberOfAccount, double balance, Currency currency) {
        this.numberOfAccount = numberOfAccount;
        this.balance = balance;
        this.currency = currency;
    }

    public Set<Transaction> getTransactionSet() {
        return transactionSet;
    }

    public void setTransactionSet(Transaction transaction) {
        transactionSet.add(transaction);
        transaction.setAccount(this);
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getNumberOfAccount() {
        return numberOfAccount;
    }

    public void setNumberOfAccount(String numberOfAccount) {
        this.numberOfAccount = numberOfAccount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account that = (Account) o;
        return accountId != null && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
