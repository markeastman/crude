package uk.me.eastmans.editor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import uk.me.eastmans.domain.Artefact;
import uk.me.eastmans.domain.Consumption;
import uk.me.eastmans.domain.Owner;

import java.util.List;

public class CrudeData {
    public static void populateDatabase(SessionFactory factory) {
        try (Session session = factory.openSession()){
            Transaction tx = session.beginTransaction();
            Owner ssoOwner = new Owner();
            ssoOwner.setName("SSO Team");
            session.persist(ssoOwner);
            Owner eapOwner = new Owner();
            eapOwner.setName("EAP Team");
            session.persist(eapOwner);
            Artefact eapArtefact = new Artefact();
            eapArtefact.setName("EAP 7");
            eapArtefact.setOwner(eapOwner);
            session.persist(eapArtefact);
            Artefact ssoArtefact = new Artefact();
            ssoArtefact.setName("RHSSO");
            ssoArtefact.setOwner(ssoOwner);
            session.persist(ssoArtefact);
            // Now create the consumption of RHSSO needing EAP
            Consumption sso2eap = new Consumption();
            sso2eap.setConsumes(eapArtefact);
            sso2eap.setConsumedBy(ssoArtefact);
            session.persist(sso2eap);
            tx.commit();
        } catch (Throwable ex) {
            System.err.println( "Failed to populate database. " + ex );
            throw new ExceptionInInitializerError(ex);
        }
    }

}
