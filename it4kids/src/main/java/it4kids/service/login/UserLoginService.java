package it4kids.service.login;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it4kids.domain.login.UserLogin;
import it4kids.service.ValidationException;

@Service
public class UserLoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginService.class);
	
	private void validate(UserLogin userLogin) throws ValidationException {
		List<String> errors = new LinkedList<String>();
		LOGGER.debug("User validation process started for " + userLogin.getEmail());
		if (StringUtils.isEmpty(userLogin.getEmail())) {
			LOGGER.debug("User validation failed: email field is empty");
			errors.add("First Name is Empty");
		}

		if (StringUtils.isEmpty(userLogin.getPassword())) {
			LOGGER.debug("User validation failed: password field is empty");
			errors.add("Last Name is Empty");
		}
		
		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

}
