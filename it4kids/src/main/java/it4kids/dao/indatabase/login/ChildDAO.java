package it4kids.dao.indatabase.login;

import java.util.List;

import it4kids.domain.login.ChildAccount;

public interface ChildDAO  {
	
	boolean childHasParent(long childId, long parentId);
	
	void addChildId(int childId);

	void addParent(int childId, int parentId);
	
	void assignParent(int childId, int parentId);

	boolean hasParentAssigned(long childId);

}
