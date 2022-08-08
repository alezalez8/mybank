package org.shunin.service;

import org.shunin.entity.Account;

public class TransactionService extends InitialService {

    private CurrencyRateService rateService;
    private AccountService accountService;


    public void transactionBetweenAccounts(Account from, Account to, double amount) {
        if (from.getBalance() < amount) {
            System.out.println("Недостаточно средств на счету");
            return;
        } else {
            double updateBalanceFrom = from.getBalance() - amount;
            double updateBalanceTo = to.getBalance() +
                    amount * rateService.currencyConvert(from.getCurrency(), to.getCurrency(), amount);
            from.setBalance(updateBalanceFrom);
            to.setBalance(updateBalanceTo);
            entityManager.persist(from);
            entityManager.persist(to);

        }
       // accountService.getCurrentAmount(from);
        // currencyConvert(Currency from, Currency to, double amount)


    }



}
