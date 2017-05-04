package it4kids.service.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;

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

	/**
	 * This method assigns a parent for a child given the child's id and
	 * parent's id.
	 * 
	 * @param childUserName
	 *            is the user name of the child for which we assign a parent
	 * @param parentUserName
	 *            is the user name of the child's parent
	 * @param request
	 *            is the input for the users data
	 * @param response
	 *            is the output in which we validate the assignment
	 */
	public void assignParent(String childUserName, String parentUserName) {
		LOGGER.debug("Assign parent process has started ");

		assign(childUserName, parentUserName);
	}
	
	public boolean hasChildAssigned(long childId, long parentId) {
		return parentDAO.parentHasChild(childId, parentId);
	}

	private void assign(String childUserName, String parentUserName) {
		int childId = 0;
		int parentId = 0;

		if (!parentDAO.hasNoChildAssigned(parentId)) {
			LOGGER.debug("parent does not have a child assigned");
			parentId = userDAO.getUserId(parentUserName);
			childId = userDAO.getUserId(childUserName);
			parentDAO.assignChild(childId, parentId);
		} else {
			LOGGER.debug("parent has a child assigned");
			childId = userDAO.getUserId(childUserName);
			parentId = userDAO.getUserId(parentUserName);
			parentDAO.addChild(childId, parentId);
		}
		if (!childDAO.hasParentAssigned(childId)) {
			LOGGER.debug("child does not have a parent assigned");
			parentId = userDAO.getUserId(parentUserName);
			childId = userDAO.getUserId(childUserName);
			childDAO.assignParent(childId, parentId);
		} else {
			LOGGER.debug("child has a parent assigned");
			childId = userDAO.getUserId(childUserName);
			parentId = userDAO.getUserId(parentUserName);
			childDAO.addParent(childId, parentId);
		}
	}

	public LinkedHashSet<Long> getChildrenId(long id) {
		return parentDAO.getChildrenId(id);
	}

	public Long getParentsId(long id) {
		return parentDAO.getParentsId(id);
	}

	public ParentAccountDAO getParentDAO() {
		return parentDAO;
	}
}
