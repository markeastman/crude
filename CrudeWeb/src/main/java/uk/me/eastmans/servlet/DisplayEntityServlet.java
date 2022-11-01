package uk.me.eastmans.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ManagedType;
import jakarta.persistence.metamodel.Metamodel;
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
        name = "DisplayEntityServlet",
        value = {"/displayEntity"}
)
public class DisplayEntityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String entityName = req.getParameter("entityName");
        String entityId = req.getParameter( "entityId" );
        EntityManager em = (EntityManager)req.getServletContext().getAttribute(CrudeServletListener.ENTITY_MANAGER);

        List<?> results = null;
        String idAttributeName = null;

        Metamodel metaModel = em.getMetamodel();
        Set<ManagedType<?>> managedTypes = metaModel.getManagedTypes();
        // We need to find the type requested so we can get the fields etc.
        for (ManagedType<?> type : managedTypes) {
            if (type instanceof EntityType<?> entityType) {
                if (entityType.getName().equals(entityName)) {
                    req.setAttribute("entityAttributes", entityType.getAttributes());
                    // Find the identifier attribute name
                    idAttributeName = "id";
                    // Try and find the specific entity with the given id
                    String jql = "select i from " + entityName + " i where " + idAttributeName + " = " + entityId;
                    Query query = em.createQuery(jql, Object.class);
                    results = query.getResultList();
                    // We should have one item or zero items
                    if (results.isEmpty()) {
                    }
                    break;
                }
            }
        }

        if (results == null) {
            // We did not manage to find the entity meta data
            req.setAttribute("entityName", entityName);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("noMetaData.jsp" );
            requestDispatcher.forward(req, resp);
        }
        else if (results.isEmpty()) {
            req.setAttribute("entityName", entityName);
            req.setAttribute("entityId", entityId);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("noEntityFound.jsp" );
            requestDispatcher.forward(req, resp);
        }
        else {
            // put out the entity
            req.setAttribute("entityName", entityName);
            req.setAttribute("entityId", entityId);
            req.setAttribute("metaModel", metaModel);
            req.setAttribute("entity", results.get(0));
            req.setAttribute("idAttributeName", idAttributeName);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("displayEntity.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}