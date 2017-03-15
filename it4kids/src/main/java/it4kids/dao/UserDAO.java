package it4kids.dao;

import it4kids.domain.login.Account;
import it4kids.domain.login.User;

/**
 * Created by Gabi on 3/1/2017.
 */
public interface UserDAO extends AccountDAO<Account> {

	/*@Override
	Collection<Account> getAll();

	@Override
	Account add(Account account, int id);*/

	public void add(User user);

	public boolean authenticateUser(String email, String password);

	public int getUsernameId(String username);

	public boolean usernameAvailable(String username);

	public void setChildId(int childId);

	public void setParentId(int parentId);


}
