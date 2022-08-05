package org.shunin.service.rateService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentRate {

    private String url = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";

    private String getQuerry() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = dateFormat.format(date);
        return url+currentDate;
    }




    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        String url = "https://api.privatbank.ua/p24api/exchange_rates?json&date=" + currentDate;
        String rate = restTemplate.getForObject(url, String.class);
        //System.out.println(rate);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(rate);
        System.out.println("==============================");
        System.out.println(obj.get("exchangeRate").get(8));
        double rateSaleEUR = (obj.get("exchangeRate").get(8).get("saleRate").asDouble());
        double ratePurchaseEUR = Double.parseDouble(obj.get("exchangeRate").get(8).get("purchaseRate").toString());
        System.out.println(obj.get("exchangeRate").get(8).get("saleRate"));
        System.out.println(obj.get("exchangeRate").get(8).get("purchaseRate"));
        System.out.println("EUR: sale - " + rateSaleEUR + "  || perchase - " + ratePurchaseEUR);


        System.out.println(obj.get("exchangeRate").get(24));


    }
}

/*
*   baseCurrency	                Базова валюта
    currency	                    Валюта угоди
    saleRateNB/purchaseRateNB	    Курс продажу НБУ
    saleRate	                    Курс продажу ПриватБанку
    purchaseRate	                Курс купівлі ПриватБанку
* */
