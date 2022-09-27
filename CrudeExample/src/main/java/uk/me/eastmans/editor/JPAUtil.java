package uk.me.eastmans.editor;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.metamodel.EntityType;

import java.util.Set;

public class JPAUtil {
    private EntityManagerFactory emf;

    public JPAUtil( String profileName ) {
        emf = Persistence.createEntityManagerFactory( profileName );
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
