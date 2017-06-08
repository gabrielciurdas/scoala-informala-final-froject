package it4kids.dao.indatabase.login;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import it4kids.dao.BaseDAO;
import it4kids.domain.UserLogin;
import it4kids.domain.login.User;

/**
 * This interface has methods which can be used to perform specific operations
 * in database with a User object.
 * 
 * @see BaseDAO
 * @see User
 * 
 * @author Gabriel Ciurdas
 * 
 * <p> Created on 03/10/2017
 */
public interface UserDAO extends BaseDAO<User> {
	
	User findByUserName(String userName);

	Collection<User> searchByName(String query);
	
	User getRegisteredUser(UserLogin userLogin);
	
	boolean userIsRegistered(String username, String password);
	
	boolean deleteParent(User user);

	boolean deleteChild(User user);
	
	Collection<User> getAllParents();

	Collection<User> getAllTeachers();

	Collection<User> getAllChildren();

	Collection<User> searchByTeacherName(String name);
	
	Collection<User> searchByParentName(String name);
	
	Collection<User> searchByChildName(String name);
	
	void add(User user) throws ServletException, IOException;
	
	void add(HttpServletRequest request);
	
	int getUsernameId(String username);

	boolean usernameAvailable(String userName);
	
	void save(User user);

	String getUserRole(String userName);

	UserLogin getUserLogin();
}
