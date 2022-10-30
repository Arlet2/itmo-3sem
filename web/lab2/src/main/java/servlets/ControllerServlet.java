package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isCoordinates(req))
            getServletContext().getNamedDispatcher("servlets.AreaCheckServlet").forward(req, resp);
        else {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
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
