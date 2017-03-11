package it4kids.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserDAO user = new UserDAO();
		String location = "";
		String accountType = "";
		if (user.usernameAvailable(request.getParameter("userName"))) {
			session.getAttribute("accountType");
			accountType = session.getAttribute("accountType").toString().toLowerCase();
            location = "user/" + accountType + "/" + accountType + "Register.jsp";
			user.add(new User(request.getParameter("firstName"), request.getParameter("lastName"),
					request.getParameter("accountType"), request.getParameter("email"),
					request.getParameter("userName"), request.getParameter("password")));

			if (user.getLinesWritten() > 0) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Înregistrare efectuată cu succes');");
				out.println("location='" + location + "';");
				out.println("</script>");

			} else {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			accountType = session.getAttribute("accountType").toString().toLowerCase();
            location = "user/" + accountType + "/" + accountType + "Register.jsp";
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Numele de utilizator există deja');");
			out.println("location='" + location + "';");
			out.println("</script>");
		}
	}
}
