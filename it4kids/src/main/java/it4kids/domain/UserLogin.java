package it4kids.domain;


import org.springframework.util.StringUtils;

import it4kids.service.ValidationException;

/**
 * This class stores basic information about an authenticated user.
 * 
 * @author Gabriel Ciurdas
 * <p> Created on 03/20/2017
 *
 */
public class UserLogin extends AbstractModel{
	private String userName;
	private String password;
	private String accountType;

	
	public UserLogin() {
		super.setId(0);
		userName = "";
		password = "";
		accountType = "";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) throws ValidationException {
			this.userName = userName;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setId(long id) {
		super.setId(id);
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) throws ValidationException {
			this.password = userName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserLogin [userName=" + userName + ", password=" + password + ", accountType=" + accountType
				+ ", getId()=" + getId() + "]";
	}
	
	
}
