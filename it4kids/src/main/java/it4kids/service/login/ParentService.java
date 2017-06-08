package it4kids.service.login;

import java.util.LinkedHashSet;

import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;
import it4kids.dao.indatabase.login.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it4kids.dao.indatabase.login.ParentDAO;

@Service
public class ParentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParentService.class);

	@Autowired
	@Qualifier("ParentAccountDAO")
	private ParentDAO parentDAO;

	@Autowired
	@Qualifier("JdbcTemplateUserDAO")
	private UserDAO dao;

	/**
	 * This method assigns a parent for a child given the child's id and
	 * parent's id.
	 * 
	 * @param childUserName
	 *            is the user name of the child for which we assign a parent
	 * @param parentUserName
	 *            is the user name of the child's parent
	 */
	public void assignParent(String childUserName, String parentUserName) {
		LOGGER.info("Assign parent process has started ");

		assign(childUserName, parentUserName);
	}
	
	public boolean hasChildAssigned(long childId, long parentId) {
		return parentDAO.parentHasChild(childId, parentId);
	}

	private void assign(String childUserName, String parentUserName) {
		int childId = 0;
		int parentId = 0;

		if (!parentDAO.hasNoChildAssigned(parentId)) {
			LOGGER.info("parent does not have a child assigned");
			parentId = dao.getUsernameId(parentUserName);
			childId = dao.getUsernameId(childUserName);
			parentDAO.assignChild(childId, parentId);
		} else {
			LOGGER.info("parent has a child assigned");
			childId = dao.getUsernameId(childUserName);
			parentId = dao.getUsernameId(parentUserName);
			parentDAO.addChild(childId, parentId);
		}
		if (!parentDAO.hasParentAssigned(childId)) {
			LOGGER.info("child does not have a parent assigned");
			parentId = dao.getUsernameId(parentUserName);
			childId = dao.getUsernameId(childUserName);
			parentDAO.assignParent(childId, parentId);
		} else {
			LOGGER.info("child has a parent assigned");
			childId = dao.getUsernameId(childUserName);
			parentId = dao.getUsernameId(parentUserName);
			parentDAO.addParent(childId, parentId);
		}
	}

	public LinkedHashSet<Long> getChildrenId(long id) {
		return parentDAO.getChildrenId(id);
	}

	public Long getParentsId(long id) {
		return parentDAO.getParentId(id);
	}

	public boolean hasParentAssigned(long childId) {
		return parentDAO.hasParentAssigned(childId);
	}

	public boolean hasParentAssigned(long childId, long parentId) {
		return parentDAO.childHasParent(childId, parentId);
	}

}
