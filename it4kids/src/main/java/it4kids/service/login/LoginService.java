package it4kids.service.login;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;
import it4kids.domain.UserLogin;
import it4kids.service.ValidationException;

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

	public void save(UserLogin user) throws ValidationException {
		validate(user.getUserName(), user.getPassword());
	}
	
	private void validate(String userName, String password) throws ValidationException {
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(userName)) {
			errors.add("Numele de utilizator nu poate fi gol.");
		}

		if (StringUtils.isEmpty(password)) {
			errors.add("Parola nu poate fi goala.");
		}
		
		if(!jdbcTemplate.userIsRegistered(userName, password)) {
			errors.add("Numele de utilizator si parola sunt invalide.");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}
}
