
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MyDAO;
import models.DVD;

@WebServlet("/JavaServlet")
public class JavaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JavaServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// initialising the print writer
		PrintWriter writer = response.getWriter();
		MyDAO dao = new MyDAO();

		try {
			ArrayList<DVD> allDvds = dao.getDVDs();

			for (DVD d : allDvds) {
				writer.write(d.getTitle());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
}
