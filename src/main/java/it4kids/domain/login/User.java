package it4kids.domain.login;

import it4kids.domain.UserLogin;

/**
 * This class stores the registered user's details.
 * 
 * @author Gabriel Ciurdas
 *<p> Created on 03/10/2017
 */
public class User extends UserLogin {
	private String firstName;
	private String lastName;
	private String email;
	private String passwordConfirm;
	private String date;
	private boolean authenticated;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public boolean getAuthenticated() {
		return authenticated;
	}
	
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
