package it4kids.service.login;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it4kids.dao.indatabase.login.UserDAO;
import it4kids.domain.login.User;

@Service
public class TeacherService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	@Qualifier("JdbcTemplateUserDAO")
	private UserDAO userDAO;

	public Collection<User> listAllParents() {
		LOGGER.debug("Listing parents ");
		return userDAO.getAllParents();
	}
	public Collection<User> search( String query) {
		LOGGER.debug("Searching for " + query);
		return userDAO.searchByName(query);
	}

	public boolean delete(long id) {
		LOGGER.debug("Deleting parent for id: " + id);
		User user = userDAO.findById(id);
		if (user != null) {
			userDAO.delete(user);
			return true;
		}

		return false;
	}
}
