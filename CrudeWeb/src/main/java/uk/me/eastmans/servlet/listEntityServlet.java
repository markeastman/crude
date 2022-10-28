package uk.me.eastmans.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.metamodel.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.metamodel.model.domain.internal.SingularAttributeImpl;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(
        name = "ListEntitiesServlet",
        value = {"/listEntity"}
)
public class listEntityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String entityName = req.getParameter("entityName");
        EntityManager em = (EntityManager)req.getServletContext().getAttribute(CrudeServletListener.ENTITY_MANAGER);
        Query query = em.createQuery("select i from " + entityName + " i", Object.class);
        List<?> results = query.getResultList();

        Metamodel metaModel = em.getMetamodel();
        Set<ManagedType<?>> managedTypes = metaModel.getManagedTypes();
        // We need to find the type requested so we can get the fields etc.
        for (ManagedType<?> type : managedTypes) {
            if (type instanceof EntityType<?> entityType) {
                if (entityType.getName().equals(entityName)) {
                    // We have the entity we want to output some column headers for it
                    // @Carlo Trying to find out what the property name of the identifier field is
//                    Set<SingularAttributeImpl> attributes = entityType.getAttributes();
//                    for (SingularAttributeImpl attribute : attributes) {
//
//                    }
                    req.setAttribute("entityAttributes", entityType.getAttributes());
//                    if (entityType.hasSingleIdAttribute() && entityType.)
//                    Set idClassAttributes = entityType.getIdClassAttributes();
                    break;
                }
            }
        }
        req.setAttribute("entityName", entityName );
        req.setAttribute( "metaModel", metaModel );
        req.setAttribute("entityList", results );

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("listEntity.jsp" );
        requestDispatcher.forward(req, resp);
    }
}