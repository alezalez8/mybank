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

    @ManyToOne(m)
    @Column(name = "from_account_id")
    private Long fromClient;

    @Column(name = "to_account_id")
    private Long toClient;

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





}
