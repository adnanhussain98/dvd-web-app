
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.MyDAO;

@WebServlet("/DeleteDvdServlet")
public class DeleteDvdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteDvdServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			// gets the value of the id key
			int id = Integer.valueOf(request.getParameter("id"));
			MyDAO dao = new MyDAO();

			try {
				dao.deleteDVD(id);
				response.sendRedirect("./GetDvdServlet");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("./LoginServlet");
		}
	}
}
