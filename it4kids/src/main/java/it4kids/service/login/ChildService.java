package it4kids.service.login;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it4kids.dao.indatabase.login.ChildDAO;
import it4kids.domain.login.ChildAccount;

@Service
public class ChildService {
	
	@Autowired
	private ChildDAO childDAO;

	public boolean hasParentAssigned(long childId, long parentId) {
		return childDAO.childHasParent(childId, parentId);
	}
	
	/*public List<ChildAccount> getAll() throws SQLException {
		return childDAO.getAll();
	}*/
	
	public boolean hasParentAssigned(long childId) {
		return childDAO.hasParentAssigned(childId);
	}

}
