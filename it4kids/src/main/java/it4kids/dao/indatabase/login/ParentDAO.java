package it4kids.dao.indatabase.login;

import java.util.LinkedHashSet;

public interface ParentDAO {

	void addParentId(int parentId);

	void addChildId(int childId);

	void addParent(int childId, int parentId);

	void addChild(int childId, int parentId);

	void assignParent(int childId, int parentId);

	void assignChild(int childId, int parentId);

	boolean parentHasChild(long childId, long parentId);

	boolean childHasParent(long childId, long parentId);

	boolean hasParentAssigned(long childId);

	LinkedHashSet<Long> getChildrenId(long parentId);

	Long getParentId(long childId);

	boolean hasNoChildAssigned(long parentId);

}
