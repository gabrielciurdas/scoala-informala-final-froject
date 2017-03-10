package it4kids.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO user = new UserDAO();
		if (user.usernameAvailable(request.getParameter("userName"))) {
			user.add(new User(request.getParameter("firstName"), request.getParameter("lastName"),
					request.getParameter("accountType"), request.getParameter("email"),
					request.getParameter("userName"), request.getParameter("password")));

			if (user.getLinesWritten() > 0) {
				request.getRequestDispatcher("validRegistration.jsp").forward(request, response);

			} else {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Numele de utilizator există deja');");
			out.println("location='user/admin/register.jsp';");
			out.println("</script>");
		}
	}
}
