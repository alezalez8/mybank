package org.shunin.entity;

import jdk.jfr.Timestamp;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "bank_account")
public class BankAccount {

    private Currency currency;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "number_account")
    private Long numberOfAccount;

    @Column(name = "createDate")
    @Timestamp
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private BankClient client;

    public BankAccount() {
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

    public Long getNumberOfAccount() {
        return numberOfAccount;
    }

    public void setNumberOfAccount(Long numberOfAccount) {
        this.numberOfAccount = numberOfAccount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BankClient getClient() {
        return client;
    }

    public void setClient(BankClient client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BankAccount that = (BankAccount) o;
        return accountId != null && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
