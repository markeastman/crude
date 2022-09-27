package uk.me.eastmans.editor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transaction;
import uk.me.eastmans.domain.Artefact;
import uk.me.eastmans.domain.Consumption;
import uk.me.eastmans.domain.Owner;

import java.util.List;

public class CrudeData {
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
            sso2eap.setConsumes(eapArtefact);
            sso2eap.setConsumedBy(ssoArtefact);
            entityManager.persist(sso2eap);
            tx.commit();
        } catch (Throwable ex) {
            System.err.println( "Failed to populate database. " + ex );
            throw new ExceptionInInitializerError(ex);
        }
    }

}
