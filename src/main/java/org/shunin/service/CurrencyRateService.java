package org.shunin.service;

import org.shunin.entity.Currency;
import org.shunin.entity.CurrencyRate;
import org.shunin.utils.CurrentRateUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Map;
import java.util.function.Supplier;

public class CurrencyRateService extends InitialService implements Runnable {
    private Map<String, Double> listOfCurrency;


    @Override
    public void run() {
        try {
            listOfCurrency = CurrentRateUtils.getAllRate();
            updateCurrencyTable();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateCurrencyTable() {
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

    public double getRateFrom(Currency from) {
        TypedQuery<Double> query = entityManager.createQuery("SELECT purchaseRate FROM CurrencyRate " +
                "WHERE currency =:currency", Double.class);
        query.setParameter("currency", from);
        return query.getSingleResult();
    }

    public double getRateTo(Currency to) {
        TypedQuery<Double> query = entityManager.createQuery("SELECT saleRate FROM CurrencyRate " +
                "WHERE currency =:currency", Double.class);
        query.setParameter("currency", to);
        return query.getSingleResult();
    }

    public double currencyConvert(Currency from, Currency to, double amount) {
        return amount * getRateFrom(from) / getRateTo(to);
    }

    public void initialBase() {
        CurrencyRate currencyRate1 = new CurrencyRate(Currency.UAH, 1, 1);
        CurrencyRate currencyRate2 = new CurrencyRate(Currency.EUR, 1, 1);
        CurrencyRate currencyRate3 = new CurrencyRate(Currency.USD, 1, 1);
        Supplier<CurrencyRate> rateSupplier = () -> {
            entityManager.persist(currencyRate1);
            entityManager.persist(currencyRate2);
            entityManager.persist(currencyRate3);
            return null;
        };
        transactionService(rateSupplier);

    }


}
