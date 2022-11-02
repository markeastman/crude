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
import uk.me.eastmans.meta.EntityMetaData;
import uk.me.eastmans.meta.MetaDataStore;

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
        MetaDataStore metaStore = (MetaDataStore)req.getServletContext().getAttribute(CrudeServletListener.META_DATA_STORE);
        EntityMetaData metaData = metaStore.getEntityMetaData( entityName );
        RequestDispatcher requestDispatcher;
        req.setAttribute("entityName", entityName);
        if (metaData != null) {
            EntityManager em = (EntityManager) req.getServletContext().getAttribute(CrudeServletListener.ENTITY_MANAGER);

            String idAttributeName = metaData.getIdentifierAttributeName();

            // Try and find the specific entity with the given id
            String jql = "select i from " + entityName + " i where " + idAttributeName + " = " + entityId;
            Query query = em.createQuery(jql, Object.class);
            List<?> results = query.getResultList();
            // We should have one item or zero items

            req.setAttribute("entityName", entityName);
            req.setAttribute("entityMetaData", metaData);

            if (results.isEmpty()) {
                req.setAttribute("entityId", entityId);
                requestDispatcher = req.getRequestDispatcher("noEntityFound.jsp" );
            }
            else {
                // put out the entity
                req.setAttribute("entityId", entityId);
                req.setAttribute("entity", results.get(0));
                requestDispatcher = req.getRequestDispatcher("displayEntity.jsp");
            }
        } else {
            // We did not manage to find the entity meta data
            requestDispatcher = req.getRequestDispatcher("noMetaData.jsp" );
        }
        requestDispatcher.forward(req, resp);
    }
}