package it4kids.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it4kids.dao.indatabase.login.ChildAccountDAO;

public class ChildService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParentService.class);
	
	@Autowired
	private ChildAccountDAO childDAO;

	public ChildAccountDAO getChildDAO() {
		return childDAO;
	}
	
	public boolean hasParentAssigned(long childId, long parentId) {
		return childDAO.childHasParent(childId, parentId);
	}
}
