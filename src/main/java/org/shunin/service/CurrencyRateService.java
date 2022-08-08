package org.shunin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.shunin.entity.CurrencyRate;
import org.shunin.utils.CurrentRateUtils;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CurrencyRateService extends InitialService implements Runnable {
    private Map<String, Double> listOfCurrency;


    @Override
    public void run() {
        try {
            listOfCurrency = CurrentRateUtils.getAllRate();
            updateCurrencyTable(listOfCurrency);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private void updateCurrencyTable(Map<String, Double> listOfCurrency) {
        Supplier<CurrencyRate> currencyRateConsumer =
                () -> {

                    for (Map.Entry<String, Double> currentRate: listOfCurrency.entrySet()
                         ) {
                        String currency = currentRate.getKey().endsWith("USD");
                    }


                        TypedQuery<CurrencyRate> query = entityManager.createQuery(
                                "UPDATE currency_rate SET sale =:sale," +
                                        "purchase =:purchase  WHERE" +
                                        " currency ='USD'",
                                CurrencyRate.class);
                        query.setParameter("sale",


                    return null;
                };
        transactionService(currencyRateConsumer);

    }


}
