package it4kids.domain;

import org.springframework.stereotype.Component;

/**
 * This class stores basic information about an authenticated user.
 * 
 * @author Gabriel Ciurdas
 * <p> Created on 03/20/2017
 *
 */
@Component
public class UserLogin extends AbstractModel{
	private String userName;
	private String password;
	private String accountType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName){
			this.userName = userName;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password){
			this.password = password;
	}

	@Override
	public String toString() {
		return "UserLogin [userName=" + userName + ", password=" + password + ", accountType=" + accountType
				+ ", getId()=" + getId() + "]";
	}
}
