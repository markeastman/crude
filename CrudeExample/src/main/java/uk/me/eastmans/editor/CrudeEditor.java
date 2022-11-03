package uk.me.eastmans.editor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import uk.me.eastmans.util.CrudeData;

import java.util.List;
import java.util.Set;

public class CrudeEditor {

    private static CrudeEditor editor;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public static void main(String[] args) {
        System.out.println( "This is a basic JPA crud editor" );
        try {
            Class c = Class.forName("uk.me.eastmans.domain.Artefact");
            System.out.println( "Class is " + c );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
                    System.out.println( "Listing entities of type '" + args[1] + "'" );
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
