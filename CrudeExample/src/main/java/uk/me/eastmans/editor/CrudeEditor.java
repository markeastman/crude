package uk.me.eastmans.editor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import uk.me.eastmans.domain.Owner;

import java.util.List;

public class CrudeEditor {

    private static CrudeEditor editor;

    private SessionFactory sessionFactory;

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
        sessionFactory = HibernateUtil.getSessionFactory();
        // As we are the example we need to populate a dummy database of entities so that we can run some queries
        CrudeData.populateDatabase(sessionFactory);
    }

    private void listEntities() {
    }

    private void listEntitiesByName( String name ) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query<Object> query = session.createQuery( "from " + name, Object.class );
            List results = query.getResultList();
            System.out.println( "Found a total of " + results );
        } catch (Throwable ex) {
            System.err.println( "Failed to create sessionFactory object. " + ex );
            throw new ExceptionInInitializerError(ex);
        }
    }
}
