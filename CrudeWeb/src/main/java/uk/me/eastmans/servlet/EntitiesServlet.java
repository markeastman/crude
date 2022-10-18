package uk.me.eastmans.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@WebServlet(
        name = "EntitiesServlet",
        value = {"/entities"}
)
public class EntitiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        EntityManager em = (EntityManager)req.getServletContext().getAttribute(CrudeServletListener.ENTITY_MANAGER);
        Metamodel metaModel = em.getMetamodel();
        final Set<EntityType<?>> entities = metaModel.getEntities();

        req.setAttribute("entities", entities );
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("entities.jsp" );
        requestDispatcher.forward(req, resp);
    }
}