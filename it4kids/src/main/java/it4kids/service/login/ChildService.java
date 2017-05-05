package it4kids.service.login;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it4kids.dao.indatabase.login.ChildDAO;
import it4kids.domain.login.ChildAccount;

@Service
public class ChildService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParentService.class);
	
	@Autowired
	private ChildDAO childDAO;

	public boolean hasParentAssigned(long childId, long parentId) {
		return childDAO.childHasParent(childId, parentId);
	}
	
	public void addChildId(int childId) throws SQLException {
		childDAO.addChildId(childId);
	}

	public void addParent(int childId, int parentId) {
		childDAO.addParent(childId, parentId);
	}
	
	public List<ChildAccount> getAll() throws SQLException {
		return childDAO.getAll();
	}
	
	public void assignParent(int childId, int parentId) {
		childDAO.assignParent(childId, parentId);
	}
	
	public boolean hasParentAssigned(long childId) {
		return childDAO.hasParentAssigned(childId);
	}

	public int getParentId(String childId) {
		return childDAO.getParentId(childId);
	}
}
