package org.shunin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.shunin.entity.Currency;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CurrentRateUtils {

    private static String url = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
    private static final int USD = 24;
    private static final int EUR = 8;


    private static String getQuerry() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = dateFormat.format(date);
        return url + currentDate;
    }

    private static JsonNode getRates() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String rate = restTemplate.getForObject(getQuerry(), String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(rate);
        return obj;
    }

    public static double getCurrencyRate(Currency currency) throws JsonProcessingException {
        int current = 0;
        switch (currency) {
            case USD:
                current = USD;
                break;
            case EUR:
                current = EUR;
                break;

        }
        return getRates().get("exchangeRate").get(current).get("saleRate").asDouble();
    }

    public static Map<String, Double> getAllRate() throws JsonProcessingException {
        Map<String, Double> listOfCurrency = new HashMap<>();
        // key - currency, value - rate

        double saleRateUSD = getRates().get("exchangeRate").get(USD).get("saleRate").asDouble();
        double purchaseRateUSD = getRates().get("exchangeRate").get(USD).get("purchaseRate").asDouble();
        double saleRateEUR = getRates().get("exchangeRate").get(EUR).get("saleRate").asDouble();
        double purchaseRateEUR = getRates().get("exchangeRate").get(EUR).get("purchaseRate").asDouble();

        listOfCurrency.put("saleRateUSD", saleRateUSD);
        listOfCurrency.put("purchaseRateUSD", purchaseRateUSD);
        listOfCurrency.put("saleRateEUR", saleRateEUR);
        listOfCurrency.put("purchaseRateEUR", purchaseRateEUR);

        return listOfCurrency;
    }


}

/*
*   baseCurrency	                Базова валюта
    currency	                    Валюта угоди
    saleRateNB/purchaseRateNB	    Курс продажу НБУ
    saleRate	                    Курс продажу ПриватБанку
    purchaseRate	                Курс купівлі ПриватБанку
* */
