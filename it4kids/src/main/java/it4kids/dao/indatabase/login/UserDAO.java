package it4kids.dao.indatabase.login;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it4kids.dao.BaseDAO;
import it4kids.domain.UserLogin;
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
	
	User getRegisteredUser(UserLogin userLogin);
	
	boolean userIsRegistered(String username, String password);
	
	boolean deleteParent(User user);

	boolean deleteChild(User user);
	
	Collection<User> getAllParents();

	Collection<User> getAllTeachers();

	Collection<User> getAllChildren();

	Collection<User> getChildren(List<Long> childrenId);
	
	Collection<User> searchByTeacherByName(String name);
	
	Collection<User> searchByParentByName(String name);
	
	Collection<User> searchByChildName(String name);
	
	void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}
