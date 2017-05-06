package it4kids.dao.indatabase.login;

import java.sql.SQLException;
import java.util.List;

import it4kids.domain.login.ChildAccount;

public interface ChildDAO {
	
	boolean childHasParent(long childId, long parentId);
	
	void addChildId(int childId) throws SQLException;

	void addParent(int childId, int parentId);
	
	List<ChildAccount> getAll() throws SQLException;
	
	public void assignParent(int childId, int parentId);
	
	boolean hasParentAssigned(long childId);

	int getParentId(String childId);
}
