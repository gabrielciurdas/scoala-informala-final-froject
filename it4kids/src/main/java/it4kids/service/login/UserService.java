package it4kids.service.login;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it4kids.dao.UserDAO;
import it4kids.domain.login.Account;
import it4kids.domain.login.User;
import it4kids.service.ValidationException;

/**
 * Created by Gabi on 3/15/2017.
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserDAO dao;

    public Collection<Account> listAll() {
    	LOGGER.debug("Listing users ");
        return dao.getAll();
    }
    
    public void save(User user) throws ValidationException {
    	LOGGER.debug("Saving user: " + user);
    	validate(user);
    	dao.add(user);
    }

    public boolean authenticateUser(String email, String password) {
    	LOGGER.debug("User to authenticate - email: " + email + ", password" + password);
        return dao.authenticateUser(email, password);
    }

    public int getUsernameId(String username) {
    	LOGGER.debug("User name to obtain: " + username);
        return dao.getUsernameId(username);
    }

    public boolean usernameAvailable(String username) {
    	LOGGER.debug("Username checked for availability: " + username);
        return dao.usernameAvailable(username);
    }

    public void setChildId(int childId) {
    	LOGGER.debug("Child id to be set: " + childId);
        dao.setChildId(childId);
    }

    public void setParentId(int parentId) {
    	LOGGER.debug("Parent id to be set: " + parentId);
        dao.setParentId(parentId);
    }
    
    private void validate(User user) throws ValidationException {
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(user.getFirstName())) {
			errors.add("Prenumele este gol");
		}

		if (StringUtils.isEmpty(user.getLastName())) {
			errors.add("Numele este gol");
		}

		if (StringUtils.isEmpty(user.getEmail())) {
			errors.add("Adresa de email este goală");
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			errors.add("Parola este goală");
		} 
		
		if (StringUtils.isEmpty(user.getUserName())) {
			errors.add("Numele de utilizator este gol");
		}
		
		if (StringUtils.isEmpty(user.getRelatedUsername())) {
			errors.add("Numele membrului de familie este gol");
		}
		
		if (StringUtils.isEmpty(user.getAccountType())) {
			errors.add("Tipul de cont este gol");
		}
		
		if (StringUtils.isEmpty(user.getId())) {
			errors.add("Id este gol");
		}
		
		if (StringUtils.isEmpty(user.getDate())) {
			errors.add("Data este goală");
		}
		
		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}
}
