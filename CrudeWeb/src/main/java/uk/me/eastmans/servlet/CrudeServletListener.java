package uk.me.eastmans.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@WebListener
public class CrudeServletListener implements ServletContextListener {
    public static final String ENTITY_MANAGER_FACTORY = "EntityManagerFactory";
    public static final String ENTITY_MANAGER = "EntityManager";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Create the entity manager and add it to the context application scope
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "Crude" );
        sce.getServletContext().setAttribute(ENTITY_MANAGER_FACTORY, emf);
        EntityManager em = emf.createEntityManager();
        sce.getServletContext().setAttribute(ENTITY_MANAGER, em);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Drop the entity manager
        ServletContext sc = sce.getServletContext();
        sc.removeAttribute(ENTITY_MANAGER_FACTORY);
        sc.removeAttribute(ENTITY_MANAGER);
    }
}
