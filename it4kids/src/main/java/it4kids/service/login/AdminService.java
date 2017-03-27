package it4kids.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;
import it4kids.domain.login.User;

public class AdminService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
	
	/*@Autowired
	private JdbcTemplateUserDAO jdbcUserDAO;*/
	
/*	public void addTeacher(User user) {
		LOGGER.debug("Adding User with Teacher Role and username" + user.getUserName());

		jdbcUserDAO.add(user);
	}*/

}
