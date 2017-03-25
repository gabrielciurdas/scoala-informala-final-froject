package it4kids.service.login;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;
import it4kids.dao.indatabase.login.ParentAccountDAO;
import it4kids.dao.indatabase.login.TeacherAccountDAO;
import it4kids.domain.login.User;

public class TeacherService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private TeacherAccountDAO teacherDAO;

	@Autowired
	private ParentAccountDAO parentDAO;

	@Autowired
	private JdbcTemplateUserDAO userDAO;

	public Collection<User> listAllParents() {
		LOGGER.debug("Listing parents ");
		return userDAO.getAllParents();
	}
	public Collection<User> search( String query) {
		LOGGER.debug("Searching for " + query);
		return userDAO.searchByName(query);
	}

	public boolean delete(int id) {
		LOGGER.debug("Deleting parent for id: " + id);
		User user = userDAO.findById(id);
		if (user != null) {
			userDAO.delete(user);
			return true;
		}

		return false;
	}
}
