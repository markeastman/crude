package uk.me.eastmans.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import uk.me.eastmans.domain.Artefact;
import uk.me.eastmans.domain.Consumption;
import uk.me.eastmans.domain.Owner;

public class CrudeData {
    public void main(String[] args) {
        System.out.println( "This is a basic JPA crud data example" );
        if (args.length > 0) {
            // Process the args
            switch (args[0]) {
                case "populateData":
                    System.out.println("Populating the database");
                    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Crude");
                    EntityManager entityManager = entityManagerFactory.createEntityManager();
                    populateDatabase(entityManager);
                    break;

                default:
                    System.err.println( "Unknown command " + args[0] );
                    break;
            }
        }
        else {
            System.err.println( "usage: .. populateData");
        }
    }
    public static void populateDatabase(EntityManager entityManager) {
        try{
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            Owner ssoOwner = new Owner();
            ssoOwner.setName("SSO Team");
            entityManager.persist(ssoOwner);
            Owner eapOwner = new Owner();
            eapOwner.setName("EAP Team");
            entityManager.persist(eapOwner);
            Artefact eapArtefact = new Artefact();
            eapArtefact.setName("EAP 7");
            eapArtefact.setOwner(eapOwner);
            entityManager.persist(eapArtefact);
            Artefact ssoArtefact = new Artefact();
            ssoArtefact.setName("RHSSO");
            ssoArtefact.setOwner(ssoOwner);
            entityManager.persist(ssoArtefact);
            // Now create the consumption of RHSSO needing EAP
            Consumption sso2eap = new Consumption();
            sso2eap.setConsumes(ssoArtefact);
            sso2eap.setConsumedBy(eapArtefact);
            entityManager.persist(sso2eap);
            tx.commit();
        } catch (Throwable ex) {
            System.err.println( "Failed to populate database. " + ex );
            throw new ExceptionInInitializerError(ex);
        }
    }

}
