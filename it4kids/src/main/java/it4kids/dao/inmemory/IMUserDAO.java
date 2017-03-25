package it4kids.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.util.StringUtils;

import it4kids.dao.inmemory.login.UserDAO;
import it4kids.domain.login.User;

public class IMUserDAO extends IMBaseDAO<User> implements UserDAO {

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
 }
