package org.shunin;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.shunin.entity.Client;
import org.shunin.entity.Currency;
import org.shunin.service.AccountService;
import org.shunin.service.ClientService;
import org.shunin.service.InitialService;
import org.shunin.utils.CurrentRateUtils;

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
        Client client = new Client("eeeeee", "rrrrrrr");
        clientService.addClient(client);
        System.out.println(CurrentRateUtils.getCurrenceRate(Currency.EUR));

       /* AccountService accountService = new AccountService();
        accountService.addAccount(222333114L, 15000, Currency.EUR, 15L);
        accountService.addAccount(22233003114L, 17000, Currency.EZK, 15L);*/


       InitialService.finish();


    }
}
