import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isCoordinates(req))
            resp.sendRedirect(req.getContextPath() + "/area_checker");
        else
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private boolean isCoordinates(HttpServletRequest req) {
        Object x = req.getParameter("x");
        Object y = req.getParameter("y");
        Object r = req.getParameter("r");

        return Optional.ofNullable(x).isPresent() &&
                Optional.ofNullable(y).isPresent() &&
                Optional.ofNullable(r).isPresent();
    }
}