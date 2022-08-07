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
    private    static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(NAME);
    protected static   EntityManager entityManager = entityManagerFactory.createEntityManager();

   /* private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;*/




   /* public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory(NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }*/


    public static void finish() {
        if (entityManager != null) entityManager.close();
        if (entityManagerFactory != null) entityManagerFactory.close();
    }


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
            throw new RuntimeException("Transaction is not finished and rollback");
        }

    }


}
