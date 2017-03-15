package it4kids.domain.login;

public class User {
	private String id;
	private String firstName;
	private String lastName;
	private String relatedUsername;
	private String email;
	private String accountType;
	private String userName;
	private String password;
	private String date;
	
	public User(String firstName, String lastName, String relatedUsername, String accountType, String email, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.relatedUsername =  relatedUsername;
		this.accountType = AccountType.valueOf(accountType).name();
		this.email = email;
		this.userName = userName;
		this.password = password;
	}
	
	
	public User(String firstName, String lastName, String accountType, String email, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountType = AccountType.valueOf(accountType).name();
		this.email = email;
		this.userName = userName;
		this.password = password;
	}

	public User() {
		firstName = "";
		lastName = "";
		accountType = "";
		email = "";
		userName = "";
		password = "";
		date = "";
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getRelatedUsername() {
		return relatedUsername;
	}
}
