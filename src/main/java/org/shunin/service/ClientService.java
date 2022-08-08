package org.shunin.service;

import org.shunin.entity.Account;
import org.shunin.entity.Client;

import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.function.Supplier;

public class ClientService extends InitialService {
    private CurrencyRateService rateService = new CurrencyRateService();


    // total summ for one client
    public double getTotalAmount(Long id) {
        Client client = entityManager.getReference(Client.class, id);
        Set<Account> accounts = client.getAccounts();
        double totalResult = 0;
        for (Account account : accounts
        ) {
            totalResult += account.getBalance() * rateService.getRateFrom(account.getCurrency());
        }
        return totalResult;
    }



    public Client findClientById(Long id) {
        return entityManager.find(Client.class, id);
    }


    public void addClient(Client client) {
        Supplier<Client> supplier = () -> {
            entityManager.persist(client);
            return client;
        };
        transactionService(supplier);
    }


    public Set<Account> getAccountsOfClient(Long id) {
        Client client = findClientById(id);
        return client.getAccounts();
    }


}
