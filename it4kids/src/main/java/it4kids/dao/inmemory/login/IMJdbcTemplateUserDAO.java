package it4kids.dao.inmemory.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it4kids.dao.indatabase.login.UserDAO;
import it4kids.dao.inmemory.IMBaseDAO;
import it4kids.domain.UserLogin;
import it4kids.domain.login.ChildAccount;
import it4kids.domain.login.ParentAccount;
import it4kids.domain.login.User;
import it4kids.service.ValidationException;
import it4kids.service.login.UserService;

public class IMJdbcTemplateUserDAO extends IMBaseDAO<User> implements UserDAO {

	private Map<Long, ParentAccount> parents = new HashMap<>();
	private Map<Long, ChildAccount> children = new HashMap<>();
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Override
	public Collection<User> searchByName(String query) {
		if (query.isEmpty()) {
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
		if (userName.isEmpty()) {
			return isValid;
		}
		Collection<User> users = new LinkedList<>(getAll());
		for (User u : users) {
			if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
				isValid = true;
			}
		}
		return isValid;
	}

	@Override
	public User findByUserName(String userName) {
		Collection<User> users = new LinkedList<>(getAll());

		User user = new User();
		boolean userFound = false;
		for (User u : users) {
			if (u.getUserName().equals(userName)) {
				user = u;
				userFound = true;
			}
		}

		if (!userFound) {
			try {
				throw new ValidationException("User " + userName + " not found.");
			} catch (ValidationException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return user;
	}

	@Override
	public User getRegisteredUser(UserLogin userLogin) {
		Collection<User> users = new LinkedList<>(getAll());

		User user = new User();
		boolean userFound = false;
		for (User u : users) {
			if (u.getUserName().equals(userLogin.getUserName()) && u.getUserName().equals(userLogin.getPassword())) {
				user = u;
				userFound = true;
			}
		}

		if (!userFound) {
			try {
				throw new ValidationException("User " + userLogin.getUserName() + " not found.");
			} catch (ValidationException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return user;
	}

	@Override
	public boolean deleteParent(User user) {
		for (Map.Entry<Long, ParentAccount> entry : parents.entrySet()) {
			if (entry.getValue().getIdChild() == user.getId()) {
				parents.remove(entry.getKey());
			}
		}

		for (Map.Entry<Long, ParentAccount> entry : parents.entrySet()) {
			if (entry.getValue().getIdRegisteredUser() == user.getId()) {
				parents.remove(entry.getKey());
			}
		}

		return false;
	}

	@Override
	public boolean deleteChild(User user) {

		for (Map.Entry<Long, ChildAccount> entry : children.entrySet()) {
			if (entry.getValue().getIdRegisteredUser() == user.getId()) {
				parents.remove(entry.getKey());
			}
		}

		for (Map.Entry<Long, ChildAccount> entry : children.entrySet()) {
			if (entry.getValue().getIdParent() == user.getId()) {
				parents.remove(entry.getKey());
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<User> getAllParents() {
		Collection<User> users = new LinkedList<>(getAll());
		@SuppressWarnings("rawtypes")
		Collection parents = new LinkedList<>();
		for (User u : users) {
			if (u.getAccountType().contains("PARENT")) {
				parents.add(u);
			}
		}
		return parents;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<User> getAllTeachers() {
		Collection<User> users = new LinkedList<>(getAll());
		@SuppressWarnings("rawtypes")
		Collection teachers = new LinkedList<>();
		for (User u : users) {
			if (u.getAccountType().equals("TEACHER")) {
				teachers.add(u);
			}
		}
		return teachers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<User> getAllChildren() {
		Collection<User> users = new LinkedList<>(getAll());
		@SuppressWarnings("rawtypes")
		Collection children = new LinkedList<>();
		for (User u : users) {
			if (u.getAccountType().equals("CHILD")) {
				children.add(u);
			}
		}
		return children;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<User> searchByTeacherName(String name) {
		Collection<User> users = new LinkedList<>(getAll());
		@SuppressWarnings("rawtypes")
		Collection teachers = new LinkedList<>();
		for (User u : users) {
			if (u.getAccountType().equals(name)) {
				teachers.add(u);
			}
		}
		return teachers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<User> searchByParentName(String name) {
		Collection<User> users = new LinkedList<>(getAll());
		@SuppressWarnings("rawtypes")
		Collection parents = new LinkedList<>();
		for (User u : users) {
			if (u.getAccountType().equals(name)) {
				parents.add(u);
			}
		}
		return parents;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<User> searchByChildName(String name) {
		Collection<User> users = new LinkedList<>(getAll());
		@SuppressWarnings("rawtypes")
		Collection children = new LinkedList<>();
		for (User u : users) {
			if (u.getAccountType().equals(name)) {
				children.add(u);
			}
		}
		return children;
	}

	@Override
	public void add(User user) throws ServletException, IOException {
		update(user);
	}

	@Override
	public void add(HttpServletRequest request) {

		UserLogin userLogin = (UserLogin) request.getSession().getAttribute("currentUser");

		String accountType;
		if (usernameAvailable(request.getParameter("userName"))) {
			accountType = userLogin.getAccountType();

			try {
				addAccount(request, accountType);
			} catch (SQLException | ServletException | IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

	}

	private void addAccount(HttpServletRequest request, String accountType)
			throws SQLException, ServletException, IOException {

		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setAccountType(request.getParameter("accountType"));
		user.setEmail(request.getParameter("email"));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));

		if (accountType.equalsIgnoreCase("PRIMARY_PARENT")) {
			add(user);
			if (request.getParameter("accountType").equalsIgnoreCase("PARENT")) {
				ParentAccount parent = new ParentAccount();
				parent.setIdRegisteredUser(getUsernameId(request.getParameter("userName")));
				parent.setId(parent.getIdRegisteredUser());
				
				parents.put((long) parent.getIdRegisteredUser(), parent);

			} else if (request.getParameter("accountType").equalsIgnoreCase("CHILD")) {
				ChildAccount child = new ChildAccount();
				child.setIdRegisteredUser(getUsernameId(request.getParameter("userName")));
				child.setId(child.getIdRegisteredUser());
				
				children.put((long) child.getIdRegisteredUser(), child);
			}

		} else if (accountType.equalsIgnoreCase("TEACHER")) {
			add(user);

			ParentAccount parent = new ParentAccount();
			parent.setIdRegisteredUser(getUsernameId(request.getParameter("userName")));
			parent.setId(parent.getIdRegisteredUser());
			
			parents.put((long) parent.getIdRegisteredUser(), parent);

		} else if (accountType.equalsIgnoreCase("ADMIN")) {
			add(user);
		}
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
	public void save(User user) {
		update(user);
	}
}
