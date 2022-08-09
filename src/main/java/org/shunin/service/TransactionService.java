package org.shunin.service;

import org.shunin.entity.Account;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TransactionService extends InitialService {

    private CurrencyRateService rateService;
    private AccountService accountService;

    public TransactionService(CurrencyRateService rateService, AccountService accountService) {
        this.rateService = rateService;
        this.accountService = accountService;
    }

    public void transactionBetweenAccounts(String accountFrom, String accountTo, double amount) {
        Supplier<Account> transAccount = () -> {
            Account from = accountService.findAccountByNumber(accountFrom);
            if (from.getBalance() < amount) {
                System.out.println("Недостаточно средств на счету");
                return null;
            } else {
                Account to = accountService.findAccountByNumber(accountTo);
                double updateBalanceFrom = from.getBalance() - amount;
                double updateBalanceTo = to.getBalance() +
                       rateService.currencyConvert(from.getCurrency(), to.getCurrency(), amount);
                from.setBalance(updateBalanceFrom);
                to.setBalance(updateBalanceTo);
                entityManager.persist(from);
                entityManager.persist(to);
                return null;
            }
        };
        transactionService(transAccount);
    }


    }



