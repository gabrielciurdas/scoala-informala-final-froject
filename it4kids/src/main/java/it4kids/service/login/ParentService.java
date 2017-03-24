package it4kids.service.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it4kids.dao.indatabase.login.ChildAccountDAO;
import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;
import it4kids.dao.indatabase.login.ParentAccountDAO;

public class ParentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParentService.class);

	@Autowired
	private ParentAccountDAO parentDAO;

	@Autowired
	private ChildAccountDAO childDAO;

	@Autowired
	private JdbcTemplateUserDAO userDAO;

	public void assignParent(String childUserName, String parentUserName, 
			HttpServletRequest request, HttpServletResponse response) {

		int childId = userDAO.getUserId(childUserName);
		int parentId = userDAO.getUserId(parentUserName);

		parentDAO.assignChild(childId, parentId);
		childDAO.assignParent(parentId, childId);
		
		try {
			validateRegistration(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void validateRegistration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if (parentDAO.getLinesWritten() > 0 && childDAO.getLinesWritten() > 0) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Asignarea a fost efectuată cu succes');");
			out.println("</script>");

		} else {
			out.println("<script charset=" + "utf-8" + "type=\"text/javascript\">");
			out.println("alert('Numele de utilizator există deja');");
			out.println("</script>");
		}
	}
}
