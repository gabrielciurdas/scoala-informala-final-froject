package it4kids.dao.indatabase.login;

import java.util.LinkedHashSet;
import java.util.List;

import it4kids.domain.login.ParentAccount;

public interface ParentDAO {
	
	void addParentId(int parentId);

	void addChild(int childId, int parentId);

	void assignChild(int childId, int parentId);

	boolean parentHasChild(long childId, long parentId);

	LinkedHashSet<Long> getChildrenId(long parentId);

	Long getParentsId(long childId);

	boolean hasNoChildAssigned(long parentId);

}
