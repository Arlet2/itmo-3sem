import data.Row;
import model.CoordinatesValidator;
import model.DataSaver;
import model.HitChecker;
import model.RowsCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {
    private final CoordinatesValidator validator = new CoordinatesValidator();
    private final HitChecker hitChecker = new HitChecker();

    private final RowsCreator rowsCreator = new RowsCreator(validator, hitChecker);

    @Override
    public void init() throws ServletException {
        validator
                .setMinX(2).setMaxX(2)
                .setMinY(-3).setMaxY(3)
                .setMinR(1).setMaxR(5);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String r = req.getParameter("r");

        Row row = rowsCreator.createRow(x, y, r);

        DataSaver.saveData(req.getSession(), row);
        getServletContext().getRequestDispatcher("/table.jsp").forward(req, resp);
    }
}
