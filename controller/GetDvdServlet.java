
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.MyDAO;
import models.DVD;

@WebServlet("/GetDvdServlet")
public class GetDvdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetDvdServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// session.setAttribute("loggedin", false);

		MyDAO dao = new MyDAO();

		try {

			// storing the dvds in an array list
			ArrayList<DVD> allDvds = dao.getDVDs();

//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yy");
//			String d = formatter.format(LocalDate.now());
//			
//			System.out.println(d);

//			request.setAttribute("adnan", d);
			request.setAttribute("dvds", allDvds);
			// where you want to take the attribute
			request.getRequestDispatcher("home2.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
