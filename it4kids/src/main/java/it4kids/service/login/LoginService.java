package it4kids.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;

@Service
public class LoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	JdbcTemplateUserDAO jdbcTemplate;

	public boolean isRegistered(String userName, String password) {
    	System.out.println("userLoginService tries to authenticate " + userName);
		boolean isValid = false;
		
		if(jdbcTemplate.userIsRegistered(userName, password)) {
			isValid = true;
			System.out.println("user " + userName + " is registered");
		}
	
		return isValid;
	}
	
	public JdbcTemplateUserDAO getJdbcTemplate() {
		return jdbcTemplate;
	}
}
