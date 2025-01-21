package com.surafel.realEstate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import property.Property;



public class EntitySaver{
	public void saveEntity(Property entity) {
        // Create session factory
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Start a transaction
            transaction = session.beginTransaction();
            // Save the entity
            session.persist(entity);
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}


    

