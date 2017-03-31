package it4kids.dao;

import it4kids.domain.login.User;

import java.util.Collection;

/**
 * Created by Gabi on 3/1/2017.
 */
public interface UserDAO extends AccountDAO<User> {

	@Override
	Collection<User> getAll();

	//@Override
	//Account add(Account account, int id);

	public void add(User user);

	public boolean authenticateUser(String userName, String password);

	public int getUsernameId(String username);

	public boolean usernameAvailable(String username);

	public void setChildId(int childId);

	public void setParentId(int parentId);

	boolean userIsRegistered(String userName, String password);

	Collection<User> searchByName(String name);

	boolean delete(User model);

	@Override
	User update(User model);

}
