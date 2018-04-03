package org.datanucleus.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import mydomain.model.MyEntity;

public class SimpleTest
{
    private EntityManagerFactory emf;
    EntityManager em;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("MyTest");
        em = emf.createEntityManager();

        // write a single entity
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        MyEntity entity = MyEntity.create(1);
        entity.setValue("foo");
        em.persist(entity);
        tx.commit();
    }

    @After
    public void tearDown() {
        emf.close();
    }

    @Test
    public void testNativeQueryAliasedPK() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // native query with table alias
        Query nativeQuery = em.createNativeQuery("select e.id, e.value from entity e where e.id = ?", MyEntity.class);
        nativeQuery.setParameter(1, 1);
        MyEntity entity = (MyEntity) nativeQuery.getResultList().get(0);
        tx.commit();
    }

    @Test
    public void testNativeQueryWildcardPK() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // native query with table alias
        Query nativeQuery = em.createNativeQuery("select * from entity  where id = ?", MyEntity.class);
        nativeQuery.setParameter(1, 1);
        MyEntity entity = (MyEntity) nativeQuery.getResultList().get(0);
        tx.commit();
    }

    @Test
    public void testNativeQuery() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // native query without table alias
        Query nativeQuery = em.createNativeQuery("select id, value from entity  where id = ?", MyEntity.class);
        nativeQuery.setParameter(1, 1);
        MyEntity entity = (MyEntity) nativeQuery.getResultList().get(0);
        tx.commit();
    }
}
