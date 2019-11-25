

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MyDAO;
import models.User;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String un = request.getParameter("username");
		String pw = request.getParameter("password1");
		
		User user = new User(un,pw, "");
		
		MyDAO dao = new MyDAO();
		try {
			dao.insertUser(user);
			response.sendRedirect("./GetDvdServlet");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}

}
