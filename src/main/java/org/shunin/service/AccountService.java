package org.shunin.service;

import org.shunin.entity.Account;
import org.shunin.entity.Client;
import org.shunin.entity.Currency;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.function.Supplier;

public class AccountService extends InitialService {


    public void addAccount(String numberOfAccount, double balance, Currency currency, Long clientId) {
        Client client = entityManager.getReference(Client.class, clientId);
        if (client != null) {
            Account account = new Account(numberOfAccount, balance, currency);
            client.setAccounts(account);
            Supplier<Client> supplier = () -> {
                entityManager.persist(client);
                return client;
            };
            transactionService(supplier);
        }
    }


    // refill account

    public void refillAccount(String numberOfAccount, double sumOfMoney) {
        double balance = sumOfMoney + getCurrentAmount(numberOfAccount);
        Supplier<Account> currentAccount = () -> {
            Query query = entityManager.createQuery("UPDATE Account SET balance=:balance" +
                    " WHERE numberOfAccount =:numberOfAccount");
            query.setParameter("balance", balance);
            query.setParameter("numberOfAccount", numberOfAccount);
            query.executeUpdate();
            return null;
        };
        transactionService(currentAccount);

    }

    public Account findAccountById(Long id) {
        return entityManager.find(Account.class, id);
    }

    public Account findAccountByNumber(String accountNumber) {
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a " +
                "WHERE numberOfAccount =:numberOfAccount", Account.class);
        query.setParameter("numberOfAccount", accountNumber);
        return query.getSingleResult();
    }

    public double getCurrentAmount(String numberOfAccount) {
        Double amount;
        TypedQuery<Double> query = entityManager.createQuery("SELECT a.balance FROM Account a " +
                " WHERE numberOfAccount =:numberOfAccount", Double.class);
        query.setParameter("numberOfAccount", numberOfAccount);
        amount = query.getSingleResult();
        return amount;
    }


}
