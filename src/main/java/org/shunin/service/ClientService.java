package org.shunin.service;

import org.shunin.entity.Account;
import org.shunin.entity.Client;

import java.util.Set;
import java.util.function.Supplier;

public class ClientService extends InitialService {

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
