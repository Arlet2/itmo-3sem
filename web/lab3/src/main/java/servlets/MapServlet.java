package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import database.AbstractPointDAO;
import database.PointDAO;
import database.Row;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mapServlet", urlPatterns = "map-handler")
public class MapServlet extends HttpServlet {
    private final AbstractPointDAO pointDao = new PointDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("mode"));
        if (!req.getParameter("mode").equals("map"))
            resp.sendError(409); // conflict

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule());
        resp.getWriter().write(objectMapper.writeValueAsString(pointDao.getPoints().toArray(new Row[0])));
    }
}