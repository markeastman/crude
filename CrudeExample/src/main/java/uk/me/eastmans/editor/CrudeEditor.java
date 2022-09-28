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
                case "populateData":
                    System.out.println( "Populating the database");
                    editor.populateData();
                    break;

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
            System.err.println( "usage: .. populateData \n       .. listEntities \n       .. listEntity nameOfEntity");
        }
    }

    public CrudeEditor()
    {
        JPAUtil util = new JPAUtil( "Crude" );
        entityManagerFactory = util.getEmf();
        entityManager = entityManagerFactory.createEntityManager();

       // Check the entities list
        listEntitiesByName( "Owner" );
    }

    public void populateData() {
        // As we are the example we need to populate a dummy database of entities so that we can run some queries
        CrudeData.populateDatabase(entityManager);
        listEntitiesByName("Owner");
    }

    public void listEntities() {
        Metamodel metaModel = entityManager.getMetamodel();
        final Set<EntityType<?>> entities = metaModel.getEntities();
        System.out.println(entities);
    }

    public void listEntitiesByName( String name ) {
        Query query = entityManager.createQuery("select i from " + name + " i", Object.class);
        List results = query.getResultList();
        System.out.println( "Found a total of " + results.size() );
    }
}
