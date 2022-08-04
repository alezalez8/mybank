package org.shunin.service;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class InitialService {

    private static final String NAME = "JPABank";
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(NAME);
    protected EntityManager entityManager = entityManagerFactory.createEntityManager();


    protected <T> T transactionService(Supplier<T> function) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            T result = function.get();
            transaction.commit();

            return result;
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
            System.out.println("Transaction is not finished and rollback");
            throw new RuntimeException("Transaction is not finished and rollback");
        }

    }


}
