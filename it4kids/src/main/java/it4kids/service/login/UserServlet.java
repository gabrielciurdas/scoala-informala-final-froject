package it4kids.service.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it4kids.dao.UserDAO;
import it4kids.dao.indatabase.login.ChildAccountDAO;
import it4kids.dao.indatabase.login.ParentAccountDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.domain.login.ChildAccount;
import it4kids.domain.login.ParentAccount;
import it4kids.domain.login.User;

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
		RegisteredUserDAO user = new RegisteredUserDAO();
		String location = "";
		String accountType = "";
		if (user.usernameAvailable(request.getParameter("userName"))) {
			session.getAttribute("accountType");
			accountType = session.getAttribute("accountType").toString().toLowerCase();
			location = "user/" + accountType + "/" + accountType + "Register.jsp";

			accountType = session.getAttribute("accountType").toString();
			try {
				addAccount(request, user, accountType);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		validateRegistration(out, session, user, location);
	}

	private void addAccount(HttpServletRequest request, UserDAO user, String accountType) throws SQLException {
		HttpSession session = request.getSession();
		if (accountType.equals("PARENT")) {
			user.add(new User(request.getParameter("firstName"), request.getParameter("lastName"),
					request.getParameter("accountType"), request.getParameter("email"),
					request.getParameter("userName"), request.getParameter("password")));
			if (request.getParameter("accountType").equals("PARENT")) {
				ParentAccountDAO p = new ParentAccountDAO();
				p.add(new ParentAccount(user.getUsernameId(request.getParameter("userName"))),
						user.getUsernameId(session.getAttribute("userName").toString()));
				
				ChildAccountDAO c = new ChildAccountDAO();
				c.add(new ChildAccount(user.getUsernameId(session.getAttribute("userName").toString())),
						user.getUsernameId(request.getParameter("userName")));
				
			} else if (request.getParameter("accountType").equals("CHILD")) {
				ChildAccountDAO c = new ChildAccountDAO();
				c.add(new ChildAccount(user.getUsernameId(request.getParameter("userName"))),
						user.getUsernameId(session.getAttribute("userName").toString()));
			}
			
		} else if (accountType.equals("TEACHER")) {
			user.add(new User(request.getParameter("firstName"), request.getParameter("lastName"),
					request.getParameter("accountType"), request.getParameter("email"),
					request.getParameter("userName"), request.getParameter("password")));
			ParentAccountDAO p = new ParentAccountDAO();
			p.add(new ParentAccount(user.getUsernameId(request.getParameter("userName"))));
			
		} else if(accountType.equals("ADMIN")) {
			user.add(new User(request.getParameter("firstName"), request.getParameter("lastName"),
					request.getParameter("accountType"), request.getParameter("email"),
					request.getParameter("userName"), request.getParameter("password")));
		}
	}

	private void validateRegistration(PrintWriter out, HttpSession session, RegisteredUserDAO user, String location) {
		String accountType;
		if (user.getLinesWritten() > 0) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Înregistrare efectuată cu succes');");
			out.println("location='" + location + "';");
			out.println("</script>");

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
