package org.shunin;

import org.shunin.entity.BankClient;
import org.shunin.service.ClientService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Starter {
    public static void main(String[] args) {
       /* EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPABank");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        BankClient bankClient = new BankClient("Aleks", "Shunin");
        BankClient client = new BankClient("Test111", "222");
        entityManager.persist(client);
        transaction.commit();*/



        ClientService clientService = new ClientService();
        System.out.println(clientService.findClientById(1L));
        System.out.println("=========================");
        BankClient client = new BankClient("eeeeee", "rrrrrrr");
        clientService.addClient(client);

    }
}
