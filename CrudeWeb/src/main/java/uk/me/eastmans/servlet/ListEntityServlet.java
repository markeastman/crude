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
import uk.me.eastmans.meta.EntityMetaData;
import uk.me.eastmans.meta.MetaDataStore;

import java.io.IOException;
import java.util.HashSet;
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
        MetaDataStore metaStore = (MetaDataStore)req.getServletContext().getAttribute(CrudeServletListener.META_DATA_STORE);
        EntityMetaData metaData = metaStore.getEntityMetaData( entityName );

        EntityManager em = (EntityManager)req.getServletContext().getAttribute(CrudeServletListener.ENTITY_MANAGER);
        Query query = em.createQuery("select i from " + entityName + " i", Object.class);
        List<?> results = query.getResultList();

        req.setAttribute("entityName", entityName );
        req.setAttribute( "entityMetaData", metaData );
        req.setAttribute("entityList", results );

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("listEntity.jsp" );
        requestDispatcher.forward(req, resp);
    }

}