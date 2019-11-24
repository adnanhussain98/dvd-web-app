
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.MyDAO;
import models.DVD;

@WebServlet("/APIServlet")
public class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public APIServlet() {
		super();
	}

	Gson gson = new Gson();
	MyDAO dao = new MyDAO();

	//params in url
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		response.setHeader("Content-Type", "application/json");

		try {
			writer.write(gson.toJson(dao.getDVDs()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dvd = request.getParameter("dvd");

		DVD d = gson.fromJson(dvd, DVD.class);
		try {
			dao.insertDVD(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//params in url
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dvd = request.getParameter("dvd");

		DVD d = gson.fromJson(dvd, DVD.class);

		try {
			dao.updateDVD(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	//params in url. ?id=6
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));

		try {
			dao.deleteDVD(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
