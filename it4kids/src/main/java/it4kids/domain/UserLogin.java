package it4kids.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserLogin {
	private long id;
	
	/*@NotEmpty
	@Size(min=4, max=30)*/
	private String userName;
	
/*	@NotEmpty
	@Size(min=4, max=30)*/
	private String password;
	
	private String accountType;
	
	public UserLogin() {
		id = 0;
		userName = "";
		password = "";
		accountType = "";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
