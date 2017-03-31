package it4kids.dao.inmemory.login;

import it4kids.dao.UserDAO;
import it4kids.domain.login.ChildAccount;
import it4kids.domain.login.ParentAccount;
import it4kids.domain.login.User;

import java.util.Collection;

public class IMUserDAO extends IMAccountDAO<User> implements UserDAO {
	
	//private UserService userService = new UserService(); //for authentication with DB 
	private final User user = new User();

	@Override
	public Collection<User> getAll() {
		return models.values();
	}
	
	/*@Override
	public User add(User model, Integer id) {
		models.put(id, model);
		return model;
	}*/

	@Override
	public void add(User user) {
		models.put(user.getId(), user);
		
	}

	@Override
	public boolean authenticateUser(String email, String password) {
		return user.getAuthenticated();
	}

	@Override
	public int getUsernameId(String username) {
		return user.getIdRegisteredUser();
	}

	@Override
	public boolean usernameAvailable(String username) {
		if(user.getAuthenticated() == false) {
			return false;
		}
		return true;
	}

	@Override
	public void setChildId(int childId) {
		ParentAccount parent = new ParentAccount();
		parent.setIdChild(childId);
	}

	@Override
	public void setParentId(int parentId) {
		ChildAccount child = new ChildAccount();
		child.setIdParent(parentId);
	}

	@Override
	public boolean userIsRegistered(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<User> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(User model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User update(User model) {
		// TODO Auto-generated method stub
		return null;
	}
 }
