package it4kids.service.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it4kids.dao.indatabase.login.ChildAccountDAO;
import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;
import it4kids.dao.indatabase.login.ParentAccountDAO;
import it4kids.domain.login.ChildAccount;

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

		int childId = 0;
		int parentId = 0;

		if(parentDAO.hasNoChildAssigned(parentId)) {
			System.out.println("does not have a child assigned");
			parentId = userDAO.getUserId(parentUserName);
			childId = userDAO.getUserId(childUserName);
			parentDAO.assignChild(childId, parentId);
		} else {
			System.out.println("has a child assigned");
			childId = userDAO.getUserId(childUserName);
			parentId = userDAO.getUserId(parentUserName);
			parentDAO.addChild(childId, parentId);
		}
		if(!childDAO.hasParentAssigned(childId)) {
			parentId = userDAO.getUserId(parentUserName);
			childId = userDAO.getUserId(childUserName);
			childDAO.assignParent(parentId, childId);
		} else {
			parentId = userDAO.getUserId(parentUserName);
			parentId = userDAO.getUserId(parentUserName);
			childDAO.addParent(parentId, childId);
		}
		
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
		
		if (parentDAO.getLinesWritten() > 0 || childDAO.getLinesWritten() > 0) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Asignarea a fost efectuata cu succes');");
			out.println("</script>");

		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Numele de utilizator exista deja');");
			out.println("</script>");
		}
	}


	public List<Long> getChildrenId(long id) {
		return parentDAO.getChildrenId(id);
	}

	public List<Long> getParentsId(long id) {
		return parentDAO.getParentsId(id);
	}
	

	public ParentAccountDAO getParentDAO() {
		return parentDAO;
	}
	/*public Collection<User> search( String query) {
		LOGGER.debug("Searching for " + query);
		return userDAO.searchByName(query);
	}

	public boolean delete(int id) {
		LOGGER.debug("Deleting parent for id: " + id);
		User user = userDAO.findById(id);
		if (user != null) {
			userDAO.delete(user);
			return true;
		}

		return false;
	}*/
}
