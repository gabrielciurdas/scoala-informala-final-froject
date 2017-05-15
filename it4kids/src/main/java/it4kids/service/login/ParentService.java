package it4kids.service.login;

import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it4kids.dao.indatabase.login.ChildDAO;
import it4kids.dao.indatabase.login.ParentDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;

@Service
public class ParentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParentService.class);

	@Autowired
	private ParentDAO parentDAO;

	@Autowired
	private ChildDAO childDAO;
	
	@Autowired
	private RegisteredUserDAO registeredUser;

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
			parentId = registeredUser.getUsernameId(parentUserName);
			childId = registeredUser.getUsernameId(childUserName);
			parentDAO.assignChild(childId, parentId);
		} else {
			LOGGER.info("parent has a child assigned");
			childId = registeredUser.getUsernameId(childUserName);
			parentId = registeredUser.getUsernameId(parentUserName);
			parentDAO.addChild(childId, parentId);
		}
		if (!childDAO.hasParentAssigned(childId)) {
			LOGGER.info("child does not have a parent assigned");
			parentId = registeredUser.getUsernameId(parentUserName);
			childId = registeredUser.getUsernameId(childUserName);
			childDAO.assignParent(childId, parentId);
		} else {
			LOGGER.info("child has a parent assigned");
			childId = registeredUser.getUsernameId(childUserName);
			parentId = registeredUser.getUsernameId(parentUserName);
			childDAO.addParent(childId, parentId);
		}
	}

	public LinkedHashSet<Long> getChildrenId(long id) {
		return parentDAO.getChildrenId(id);
	}

	public Long getParentsId(long id) {
		return parentDAO.getParentsId(id);
	}

}
