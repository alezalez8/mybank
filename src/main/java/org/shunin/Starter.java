package org.shunin;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.shunin.entity.BankClient;
import org.shunin.entity.Currency;
import org.shunin.service.ClientService;
import org.shunin.service.rateService.CurrentRate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Starter {
    public static void main(String[] args) throws JsonProcessingException {
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
        System.out.println(CurrentRate.getCurrenceRate(Currency.EUR));

    }
}
