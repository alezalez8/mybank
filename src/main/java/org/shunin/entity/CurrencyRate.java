package org.shunin.entity;

import javax.persistence.*;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "currency_id")
    private Long currencyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "sale")
    private double saleRate;

    @Column(name = "purchase")
    private double purchaseRate;



    public CurrencyRate() {
    }

    public CurrencyRate(Currency currency, double saleRate, double purchaseRate) {
        this.currency = currency;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
    }

    public double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(double saleRate) {
        this.saleRate = saleRate;
    }

    public double getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(double purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


}
