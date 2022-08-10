package org.shunin.service;

import org.shunin.entity.Account;
import org.shunin.entity.Currency;
import org.shunin.entity.Transaction;

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

                Transaction transaction = new Transaction(accountFrom, accountTo, rateService.getRateFrom(from.getCurrency()),
                        rateService.getRateTo(to.getCurrency()), amount, from.getCurrency(), to.getCurrency());
                from.setTransactionSet(transaction);
                entityManager.persist(transaction);
                return null;
            }
        };
        transactionService(transAccount);
    }

    public void addMoneyToAccountFromBank(String accountTo, double amount) {
        Supplier<Account> transAccount = () -> {
            Account to = accountService.findAccountByNumber(accountTo);

            double updateBalanceTo = to.getBalance() +
                    rateService.currencyConvert(Currency.UAH, to.getCurrency(), amount);

            to.setBalance(updateBalanceTo);
            entityManager.persist(to);

            Transaction transaction = new Transaction(accountTo,
                    rateService.getRateTo(to.getCurrency()), amount, to.getCurrency());
            to.setTransactionSet(transaction);
            entityManager.persist(transaction);
            return null;

        };
        transactionService(transAccount);
    }


}



