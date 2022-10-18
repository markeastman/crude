package uk.me.eastmans.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        List results = query.getResultList();

        req.setAttribute("entityName", entityName);
        req.setAttribute("entityList", results );

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("listEntity.jsp" );
        requestDispatcher.forward(req, resp);
    }
}