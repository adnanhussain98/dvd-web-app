
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.MyDAO;
import models.DVD;

@WebServlet("/InsertNewDvdServlet")
public class InsertNewDvdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertNewDvdServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (null != session.getAttribute("loggedin") && (boolean) session.getAttribute("loggedin")) {

			// int id = Integer.valueOf(request.getParameter("id"));
			String title = request.getParameter("title");
			String genre = request.getParameter("genre");
			int year = Integer.valueOf(request.getParameter("year"));

			// created object of DVD class
			DVD dvd = new DVD(88, title, genre, year);

			MyDAO dao = new MyDAO();

			try {
				dao.insertDVD(dvd);
				//inserts DVD is the user is logged in
				response.sendRedirect("./GetDvdServlet");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			//redirects user to login if they haven't logged in
			response.sendRedirect("./LoginServlet");
		}
	}

}
