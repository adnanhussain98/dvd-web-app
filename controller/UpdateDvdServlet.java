
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.MyDAO;
import models.DVD;

@WebServlet("/UpdateDvdServlet")
public class UpdateDvdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateDvdServlet() {
		super();
	}

	MyDAO dao = new MyDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			int id = Integer.valueOf(request.getParameter("id"));

			try {
				DVD dvd = dao.getSingleDvd(id);
				request.setAttribute("dvd", dvd);
				request.getRequestDispatcher("update2.jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("./LoginServlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			int id = Integer.valueOf(request.getParameter("id"));
			String title = request.getParameter("title");
			String genre = request.getParameter("genre");
			int year = Integer.valueOf(request.getParameter("year"));

			DVD dvd = new DVD(id, title, genre, year);

			try {
				dao.updateDVD(dvd);
				response.sendRedirect("./GetDvdServlet");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("./LoginServlet");
		}
	}

}
