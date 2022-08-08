package org.shunin.service;

import org.shunin.entity.CurrencyRate;
import org.shunin.utils.CurrentRateUtils;

import javax.persistence.Query;
import java.util.Map;
import java.util.function.Supplier;

public class CurrencyRateService extends InitialService implements Runnable {
    private Map<String, Double> listOfCurrency;


    @Override
    public void run() {
        try {
            listOfCurrency = CurrentRateUtils.getAllRate();
            updateCurrencyTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void updateCurrencyTable() {

        Supplier<CurrencyRate> rateSupplier = () -> {

            Query query = entityManager.createQuery(
                    "UPDATE CurrencyRate SET saleRate =:sale, purchaseRate =:purchase " +
                            " WHERE currency ='USD'");
            query.setParameter("sale", listOfCurrency.get("saleRateUSD"));
            query.setParameter("purchase", listOfCurrency.get("purchaseRateUSD"));
            query.executeUpdate();


            query = entityManager.createQuery(
                    "UPDATE CurrencyRate SET saleRate =:sale, purchaseRate =:purchase " +
                            " WHERE currency ='EUR'");
            query.setParameter("sale", listOfCurrency.get("saleRateEUR"));
            query.setParameter("purchase", listOfCurrency.get("purchaseRateEUR"));
            query.executeUpdate();
            return null;
        };

        transactionService(rateSupplier);
    }

}
