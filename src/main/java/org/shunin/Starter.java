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



        ClientService clientService = new ClientService();
        //System.out.println(clientService.findClientById(1L));
        System.out.println("=========================");
      /*  Client client1 = new Client("Elena", "Lebedinskay");
        Client client2 = new Client("Aleks", "Shunin");
        Client client3 = new Client("Gena", "Beloys");
        Client client4 = new Client("Yuriy", "Radov");
        Client client5 = new Client("Artem", "Shunin");*/

       /* clientService.addClient(client1);
        clientService.addClient(client2);
        clientService.addClient(client3);
        clientService.addClient(client4);
        clientService.addClient(client5);*/
        System.out.println(CurrentRateUtils.getCurrenceRate(Currency.EUR));

        AccountService accountService = new AccountService();
        accountService.addAccount("222330653114", 15000, Currency.EUR, 4L);
        accountService.addAccount("222363003114", 20458, Currency.GRN, 5L);
        accountService.addAccount("222808003114", 24850, Currency.EUR, 5L);
        accountService.addAccount("222334543114", 22450, Currency.USD, 8L);


       InitialService.finish();


    }
}
