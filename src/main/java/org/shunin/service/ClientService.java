package org.shunin.service;

import org.shunin.entity.BankClient;

import java.util.function.Supplier;

public class ClientService extends InitialService {

    public BankClient findClientById(Long id) {
     //   Supplier<BankClient> supplier = () -> {
            BankClient client = entityManager.find(BankClient.class, id);
            return client;
     /*   };
        return transactionService(supplier);*/
    }

    public void addClient(BankClient client) {
        Supplier<BankClient> supplier = () -> {
            entityManager.persist(client);
            return client;
        };
        transactionService(supplier);


    }

}
