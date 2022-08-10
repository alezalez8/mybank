package org.shunin;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.shunin.entity.Client;
import org.shunin.entity.Currency;
import org.shunin.service.*;

public class Starter {
    public static void main(String[] args) throws JsonProcessingException {


        CurrencyRateService rateService = new CurrencyRateService();
        ClientService clientService = new ClientService();
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService(rateService, accountService);

        System.out.println("Идет соединение с банком, время ожидания не более 60 сек");
        // rateService.run();
        System.out.println("Актуальный курс валют получен");

        // --- для получения, к примеру, раз в 10 минут текущего курса от ПриватБанка, на "вырост" программы
        /*Thread thread = new Thread(rateService);
        thread.setDaemon(true);
        thread.start();*/


        //System.out.println(clientService.findClientById(1L));
        //  ----------------------- ADD CLIENTS TO DATABASE -----------------------------------
        clientService.addClient(new Client("Elena", "Lebedinskay"));
        clientService.addClient(new Client("Aleks", "Shunin"));
        clientService.addClient(new Client("Gena", "Beloys"));
        clientService.addClient(new Client("Yuriy", "Radov"));
        clientService.addClient(new Client("Artem", "Shunin"));

        //  ----------------------- ADD ACCOUNTS TO DATABASE -----------------------------------
        accountService.addAccount("222330653114", 15000, Currency.EUR, 4L);
        accountService.addAccount("222363003114", 20458, Currency.UAH, 5L);
        accountService.addAccount("222808003114", 24850, Currency.EUR, 5L);
        accountService.addAccount("222334543114", 22450, Currency.USD, 8L);




        System.out.println("++++++++++++++++++++++++++++++++++++++");
        System.out.println(clientService.getTotalAmount(5L));
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        // accountService.refillAccount("222808003114", 15500L);
        System.out.println(accountService.getCurrentAmount("EUR444555666"));
        System.out.println(accountService.getCurrentAmount("USD111222333"));


        // System.out.println(accountService.findAccountByNumber("EUR444555666"));

        transactionService.transactionBetweenAccounts("EUR444555666", "USD111222333", 10L);
        transactionService.transactionBetweenAccounts("EUR777888999", "UAH111222333", 50L);
        transactionService.transactionBetweenAccounts("UAH111222333", "UAH111222333", 20L);

        //  --------------------пополнение счета клиента самим банком ---------------------------
        transactionService.addMoneyToAccountFromBank("UAH111222333", 20L);
        transactionService.addMoneyToAccountFromBank("EUR777888999", 33L);


        InitialService.finish();


    }
}
