package it4kids.dao.inmemory.login;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import it4kids.domain.login.ChildAccount;
import org.springframework.stereotype.Repository;

import it4kids.dao.indatabase.login.ParentDAO;
import it4kids.domain.login.ParentAccount;

@Repository
public class IMParentAccountDAO extends IMJdbcTemplateUserDAO implements ParentDAO {
	private Map<Long, ParentAccount> parents = new HashMap<>();
	private Map<Long, ChildAccount> children = new HashMap<>();

	@Override
	public void addParentId(int parentId) {
		parents.put((long) parentId, new ParentAccount(parentId));
	}

	@Override
	public void addChildId(int childId) {
		children.put((long) childId, new ChildAccount(childId));
	}

	@Override
	public void addParent(int childId, int parentId) {
		children.get(childId).setIdParent(parentId);
	}

	@Override
	public void addChild(int childId, int parentId) {
		parents.get(parentId).setIdChild(childId);
	}

	@Override
	public void assignParent(int childId, int parentId) {
		children.get(childId).setIdParent(parentId);
	}

	@Override
	public void assignChild(int childId, int parentId) {
		parents.get(parentId).setIdChild(childId);
	}

	@Override
	public boolean parentHasChild(long childId, long parentId) {
		boolean hasChild = false;
		if(parents.get(parentId).getIdChild() == childId) {
			hasChild = true;
		}

		return hasChild;
	}

	@Override
	public boolean childHasParent(long childId, long parentId) {

		for (Map.Entry<Long, ChildAccount> entry: children.entrySet()) {
			if (entry.getValue().getIdRegisteredUser() == childId
					&& entry.getValue().getIdParent() == parentId) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasParentAssigned(long childId) {
		for (Map.Entry<Long, ChildAccount> entry: children.entrySet()) {
			if (entry.getValue().getIdRegisteredUser() == childId
					&& entry.getValue().getIdParent() == 0) {
				return false;
			}
		}

		return true;
	}


	@Override
	public LinkedHashSet<Long> getChildrenId(long parentId) {
		LinkedHashSet<Long> childrenId = new LinkedHashSet<>();

		for (Map.Entry<Long, ParentAccount> entry: parents.entrySet()) {
			if (entry.getKey() == parentId) {
				childrenId.add((long) entry.getValue().getIdChild());
			}
		}

		return  childrenId;
	}

	@Override
	public Long getParentId(long childId) {

		for (Map.Entry<Long, ChildAccount> entry: children.entrySet()) {
			if (entry.getKey() == childId) {
				return Long.valueOf(entry.getValue().getIdParent());
			}
		}

		return null;
	}

	@Override
	public boolean hasNoChildAssigned(long parentId) {
		boolean hasChildAssigned = false;

		for (Map.Entry<Long, ParentAccount> entry: parents.entrySet()) {
			if (entry.getKey() == parentId) {
				if (entry.getValue().getIdChild() > 0) {
					hasChildAssigned = true;
				}
			}
		}
		return hasChildAssigned;
	}
	
	public Map<Long, ParentAccount> getParents() {
		return parents;
	}

}
