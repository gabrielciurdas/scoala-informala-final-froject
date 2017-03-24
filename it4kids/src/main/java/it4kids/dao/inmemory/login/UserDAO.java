package it4kids.dao.inmemory.login;

import java.util.Collection;

import it4kids.dao.BaseDAO;
import it4kids.domain.login.User;

/**
 * Created by Gabi on 3/1/2017.
 */
public interface UserDAO extends BaseDAO<User> {

	Collection<User> searchByName(String query);
	
	boolean userIsRegistered(String userName, String password);
	
	//void add(User user);
	
}
