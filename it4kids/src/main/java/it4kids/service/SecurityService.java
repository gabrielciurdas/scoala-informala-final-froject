package it4kids.service;

import it4kids.domain.UserLogin;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

private ThreadLocal<UserLogin> currentUser;
	
	public void setCurrentUser(UserLogin userLogin) {
		this.currentUser = new ThreadLocal<>();
		this.currentUser.set(userLogin);
	}
	
	public UserLogin getCurrentUser() {
		return this.currentUser != null ?
				this.currentUser.get() : null;
	}
}
