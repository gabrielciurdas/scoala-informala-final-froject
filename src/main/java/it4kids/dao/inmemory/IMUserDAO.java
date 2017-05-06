package it4kids.dao.inmemory;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import it4kids.dao.indatabase.login.UserDAO;
import it4kids.domain.UserLogin;
import it4kids.domain.login.User;

/**
 * This class performs specific operations in memory on a User object.
 * 
 * @see IMBaseDAO
 * @see User
 * 
 * @author Gabriel Ciurdas
 * 
 * <p> Created on 03/10/2017
 */
@Component(value="IMUserDAO")
public class IMUserDAO extends IMBaseDAO<User> implements UserDAO{
	
	@Override
	public Collection<User> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}
		Collection<User> users = new LinkedList<User>(getAll());
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			String s = user.getFirstName() + " " + user.getLastName();
			if (!s.toLowerCase().contains(query.toLowerCase())) {
				iterator.remove();
			}
		}
		return users;
	}

	@Override
	public boolean userIsRegistered(String userName, String password) {
		boolean isValid = false;
		if (StringUtils.isEmpty(userName)) {
			return isValid;
		}
		Collection<User> users = new LinkedList<User>(getAll());
		for(User u: users) {
			if(u.getUserName().equals(userName) && u.getPassword().equals(password)) {
				isValid = true;
			}
		}
		return isValid;
	}

	@Override
	public User getRegisteredUser(UserLogin userLogin) {
		return null;
	}
	
	@Override
	public boolean deleteParent(User user) {
		return false;
	}
	
	@Override
	public boolean deleteChild(User user) {
		return false;
	}

	@Override
	public Collection<User> getAllParents() {
		return null;
	}

	@Override
	public Collection<User> getAllTeachers() {
		return null;
	}

	@Override
	public Collection<User> getAllChildren() {
		return null;
	}

	@Override
	public Collection<User> getChildren(List<Long> childrenId) {
		return null;
	}

	@Override
	public Collection<User> searchByTeacherByName(String name) {
		return null;
	}

	@Override
	public Collection<User> searchByParentByName(String name) {
		return null;
	}

	@Override
	public Collection<User> searchByChildName(String name) {
		return null;
	}

	@Override
	public void add(HttpServletRequest request) throws ServletException, IOException {
		
	}

	@Override
	public User findByUserName(String userName) {
		return null;
	}

	@Override
	public void add(User user) throws ServletException, IOException {
		
	}

	@Override
	public int getUsernameId(String username) {
		return 0;
	}

	@Override
	public boolean usernameAvailable(String userName) {
		return false;
	}

	@Override
	public String getUserRole(String userName) {
		return null;
	}

	@Override
	public String getUserAccountTye(String userName) {
		return null;
	}

	@Override
	public boolean userExists(int id) {
		return false;
	}

	@Override
	public void setChildId(int childId) {
		
	}

	@Override
	public void setParentId(int parentId) {
		
	}

	@Override
	public boolean userNameNotTaken(String userName) {
		return false;
	}

	@Override
	public void save(User user) {
		
	}
 }