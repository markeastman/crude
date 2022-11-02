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

import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(
        name = "ListEntityServlet",
        value = {"/listEntity"}
)
public class ListEntityServlet extends HttpServlet {

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
                   req.setAttribute("entityAttributes", entityType.getAttributes());
                    // We need to get the name of the identifier property
                    String idAttributeName = getIdentifierPropertyName(entityType);
                    // We need to output the name of the identifier attribute
                    req.setAttribute( "idAttributeName", idAttributeName );
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

    private <T> String getIdentifierPropertyName( EntityType<T> entityType)
    {
//        final Set<Attribute<? super T, ?>> attrs = entityType.getAttributes();
        final Set<SingularAttribute<? super T, ?>> singleAttrs = entityType.getSingularAttributes();
        for (SingularAttribute attribute : singleAttrs) {
            if (attribute.isId())
                return attribute.getName();
        }
        return null;
    }
}