package uk.me.eastmans.editor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;

import java.util.List;
import java.util.Set;

public class CrudeEditor {

    private static CrudeEditor editor;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public static void main(String[] args) {
        System.out.println( "This is a basic JPA crud editor" );
        if (args.length > 0) {
            if (editor == null)
                editor = new CrudeEditor();
            // Process the args
            switch (args[0]) {
                case "listEntities":
                    System.out.println( "Listing all entity types" );
                    editor.listEntities();
                    break;

                case "listEntity":
                    System.out.println( "Listing enitities of type '" + args[1] + "'" );
                    editor.listEntitiesByName( args[1] );
                    break;

                default:
                    System.err.println( "Unknown command " + args[0] );
                    break;
            }
        }
        else {
            System.err.println( "usage: .. listEntities \n       .. listEntity nameOfEntity");
        }
    }

    private CrudeEditor()
    {
        //Todo At the moment pass null so that we select from the hibernate.cfg.xml as default
        //Todo later we can pass a URL to an external deinition
        JPAUtil util = new JPAUtil( "Crude" );
        entityManagerFactory = util.getEmf();
        entityManager = entityManagerFactory.createEntityManager();

        // As we are the example we need to populate a dummy database of entities so that we can run some queries
        CrudeData.populateDatabase(entityManager);
    }

    private void listEntities() {
        Metamodel metaModel = entityManager.getMetamodel();
        final Set<EntityType<?>> entities = metaModel.getEntities();
        System.out.println(entities);
    }

    private void listEntitiesByName( String name ) {
        JPAUtil util = new JPAUtil( "Crude" );
        EntityManagerFactory emf = util.getEmf();
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("from " + name, Object.class);
        List results = query.getResultList();
            System.out.println( "Found a total of " + results );
    }
}
