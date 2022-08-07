package org.shunin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.shunin.entity.Currency;

public class CurrencyConverter {

    private Currency currency;
    private double getFromAccount;
    private double sendToAccount;

    public CurrencyConverter(Currency currency, double getFromAccount, double sendToAccount) {
        this.currency = currency;
        this.getFromAccount = getFromAccount;
        this.sendToAccount = sendToAccount;
    }

    public double convertFromAccount(double getFromAccount, Currency currency) throws JsonProcessingException {
        return getFromAccount * CurrentRateUtils.getCurrenceRate(currency);
    }

    public double convertToAccount(double sendToAccount, Currency currency) {
        return 0;
    }
}
