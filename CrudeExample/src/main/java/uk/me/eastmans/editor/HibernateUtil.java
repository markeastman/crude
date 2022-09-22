package uk.me.eastmans.editor;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.net.URL;

public class HibernateUtil {
    private SessionFactory sessionFactory;
    private Metadata metaData;

    public HibernateUtil(URL configLocation)
    {
        try {
            // Create the SessionFactory from hibernate.cfg.xml

            // A SessionFactory is set up once for an application!
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            if (configLocation != null)
                builder.configure( configLocation );
            else
                builder.configure();
            StandardServiceRegistry registry = builder.build();
            try {
                metaData = new MetadataSources( registry ).buildMetadata();
                sessionFactory = metaData.buildSessionFactory();
            }
            catch (Exception e) {
                System.err.println("Cannot process configuration" + e);
                // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
                // so destroy it manually.
                StandardServiceRegistryBuilder.destroy( registry );
                throw new ExceptionInInitializerError(e);
            }
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Metadata getMetaData() { return metaData; }
}
