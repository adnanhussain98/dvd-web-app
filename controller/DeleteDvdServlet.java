
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MyDAO;
import models.DVD;

@WebServlet("/DeleteDvdServlet")
public class DeleteDvdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteDvdServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		int year = Integer.valueOf(request.getParameter("year"));

		DVD dvd = new DVD(id, title, genre, year);

		MyDAO dao = new MyDAO();

		try {
			dao.deleteDVD(dvd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
