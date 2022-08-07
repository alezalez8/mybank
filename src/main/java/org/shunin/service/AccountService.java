package org.shunin.service;

import org.shunin.entity.Account;
import org.shunin.entity.Client;
import org.shunin.entity.Currency;

import java.util.function.Supplier;

public class AccountService extends InitialService {


    public void addAccount(String numberOfAccount, double balance, Currency currency, Long clientId) {
        Client client = entityManager.getReference(Client.class, clientId);
        if (client != null) {
            Account account = new Account(numberOfAccount, balance, currency);
            client.setAccounts(account);
            Supplier<Client> supplier =  () -> {
                entityManager.persist(client);
                return client;
            };
            transactionService(supplier);
        }


    }

    public Account findAccountById(Long id) {
        return entityManager.find(Account.class, id);
    }

   /* public Set<Account> getAllClientAccounts(Client client) {


    }*/


}
