package it4kids.dao.indatabase.login;

import it4kids.domain.UserLogin;
import it4kids.domain.login.User;

public interface RegisteredUserDAO {
	
	void add(User user);
	
	int getUsernameId(String username);

	boolean usernameAvailable(String userName);
	
	String getUserRole(String userName);
	
	UserLogin getUserLogin();
}
