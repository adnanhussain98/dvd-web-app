
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.MyDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String un = request.getParameter("username");
		String pw = request.getParameter("password");

		MyDAO dao = new MyDAO();

		try {
			if (dao.validateUserInfo(un, pw)) {
				HttpSession session = request.getSession();
				session.setAttribute("loggedin", true);
				//creates object "username" of un 
				session.setAttribute("username", un);
				response.sendRedirect("./GetDvdServlet");
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
