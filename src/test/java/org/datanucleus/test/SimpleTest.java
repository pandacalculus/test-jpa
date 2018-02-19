package org.datanucleus.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

import mydomain.model.MyEntity;

public class SimpleTest
{
    private EntityManagerFactory emf;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("MyTest");
    }

    @After
    public void tearDown() {
        emf.close();
    }

    @Test
    public void testVersionLocking()
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        MyEntity entity1 = MyEntity.create("1");
        MyEntity entity2 = MyEntity.create("2");
        em.persist(entity1);
        em.persist(entity2);
        tx.commit();

        tx = em.getTransaction();
        tx.begin();
        MyEntity ent1 = em.find(MyEntity.class, "1");
        MyEntity ent2 = em.find(MyEntity.class, "2");
        em.lock(ent1, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        em.lock(ent2, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        tx.commit();
    }
}
