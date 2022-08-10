package org.shunin;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.shunin.entity.Client;
import org.shunin.entity.Currency;
import org.shunin.entity.CurrencyRate;
import org.shunin.service.*;

public class Starter {
    public static void main(String[] args) throws JsonProcessingException {


        CurrencyRateService rateService = new CurrencyRateService();
        rateService.initialBase();
        ClientService clientService = new ClientService();
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService(rateService, accountService);


        System.out.println("Идет соединение с банком, время ожидания не более 60 сек");
         rateService.run();
        System.out.println("Актуальный курс валют получен");

        // --- для получения, к примеру, раз в 10 минут текущего курса от ПриватБанка, на "вырост" программы
        /*Thread thread = new Thread(rateService);
        thread.setDaemon(true);
        thread.start();*/


        //  ----------------------- ADD CLIENTS TO DATABASE -----------------------------------
        clientService.addClient(new Client("Elena", "Lebedinskay"));
        clientService.addClient(new Client("Aleks", "Shunin"));
        clientService.addClient(new Client("Gena", "Beloys"));
        clientService.addClient(new Client("Yuriy", "Radov"));
        clientService.addClient(new Client("Artem", "Shunin"));

        //  ----------------------- ADD ACCOUNTS TO DATABASE -----------------------------------
        accountService.addAccount("USD11111111", 5800, Currency.USD, 1L);
        accountService.addAccount("EUR11111111", 9400, Currency.EUR, 1L);
        accountService.addAccount("UAH11111111", 10000, Currency.UAH, 1L);
        accountService.addAccount("UAH22222222", 20200, Currency.UAH, 2L);
        accountService.addAccount("EUR22222222", 6850, Currency.EUR, 2L);
        accountService.addAccount("USD33333333", 3820, Currency.USD, 3L);
        accountService.addAccount("UAH44444444", 15500, Currency.UAH, 4L);
        accountService.addAccount("UAH44444445", 11200, Currency.UAH, 4L);
        accountService.addAccount("USD44444444", 4800, Currency.USD, 4L);
        accountService.addAccount("EUR55555555", 2300, Currency.EUR, 5L);
        accountService.addAccount("USD55555555", 5000, Currency.USD, 5L);


        System.out.println("+++++++++++++ Get balance from  accounts of client with id = 1 +++++++++");
        System.out.println("USD = " + accountService.getCurrentAmount("USD11111111"));
        System.out.println("EUR = " + accountService.getCurrentAmount("EUR11111111"));
        System.out.println("UAH = " + accountService.getCurrentAmount("UAH11111111"));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");


        System.out.println("+++++++++++++ Пополнение счета самим банком (через терминал),++++++++++");
        System.out.println("+++++++++++++ пополнение в гривне, конвертация автоматически ++++++++++");
        transactionService.addMoneyToAccountFromBank("UAH11111111", 2000L);
        transactionService.addMoneyToAccountFromBank("EUR11111111", 1000L);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        System.out.println("+++++++++++++ Вывод обновленных счетов с новым балансом +++++++++++++++");
        System.out.println("EUR = " + accountService.getCurrentAmount("EUR11111111"));
        System.out.println("UAH = " + accountService.getCurrentAmount("UAH11111111"));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");


        System.out.println("+++++++++++++ Получение сумму со всех счетов клиента с учетом курса ++++");
        System.out.println("Total sum (UAH) = " + clientService.getTotalAmount(1L));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");


        System.out.println("+++++++++++++ Перевод между счетами одного клиента  ++++++++++++++++++++");
        transactionService.transactionBetweenAccounts("EUR55555555", "USD55555555", 100L);

        System.out.println("+++++++++++++ Обновленныe данные по счету EUR55555555, USD55555555 +++++\n");
        System.out.println("EUR = " + accountService.getCurrentAmount("EUR55555555"));
        System.out.println("USD = " + accountService.getCurrentAmount("USD55555555"));
        System.out.println();

        System.out.println("+++++++++++++ Перевод между счетами разных клиентов  +++++++++++++++++++");
        transactionService.transactionBetweenAccounts("UAH11111111", "USD33333333", 200L);

        System.out.println("+++++++++++++ Обновленныe данные по счету UAH11111111, USD33333333 +++++\n");
        System.out.println("EUR = " + accountService.getCurrentAmount("UAH11111111"));
        System.out.println("USD = " + accountService.getCurrentAmount("USD33333333"));


        InitialService.finish();

    }
}
