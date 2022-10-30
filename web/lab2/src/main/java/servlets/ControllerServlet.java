package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isCoordinates(req))
            getServletContext().getNamedDispatcher("AreaCheckServlet").forward(req, resp);
        else {
            resp.sendError(403); // forbidden
        }
    }

    private boolean isCoordinates(HttpServletRequest req) {
        Object x = req.getParameter("x");
        Object y = req.getParameter("y");
        Object r = req.getParameter("r");

        return x != null &&
                y != null &&
                r != null;
    }
}
