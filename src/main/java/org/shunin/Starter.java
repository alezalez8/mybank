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
        entityManager.persist(bankClient);
        transaction.commit();*/
        ClientService clientService = new ClientService();
        System.out.println(clientService.findClientById(1L));
        System.out.println("=========================");
        BankClient client = new BankClient("Testname", "fammmm");
        clientService.addClient(client);

    }
}
