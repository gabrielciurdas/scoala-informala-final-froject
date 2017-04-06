package it4kids.dao.indatabase.login;

import java.util.Collection;

import it4kids.dao.BaseDAO;
import it4kids.domain.login.User;

/**
 * This class performs specific operations in database with a User object.
 * 
 * @see BaseDAO
 * @see User
 * 
 * @author Gabriel Ciurdas
 * 
 * <p> Created on 03/10/2017
 */
public interface UserDAO extends BaseDAO<User> {

	Collection<User> searchByName(String query);
	
	boolean userIsRegistered(String userName, String password);
	
}
