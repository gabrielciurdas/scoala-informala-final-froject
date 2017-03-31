package it4kids.domain.login;

public class User extends Account {
	private String id;
	private String firstName;
	private String lastName;
	private String relatedUsername;
	private String email;
	private String accountType;
	private String userName;
	private String password;
	private String date;
	private boolean authenticated;
	
	public User(String firstName, String lastName, String relatedUsername, String accountType, String email, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.relatedUsername =  relatedUsername;
		this.accountType = AccountType.valueOf(accountType).name();
		this.email = email;
		this.userName = userName;
		this.password = password;
		authenticated = false;
	}
	
	public User(String email, String password) {
		super();
		this.email = email;
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
		authenticated = false;
	}

	public User() {
		firstName = "";
		lastName = "";
		accountType = "";
		email = "";
		userName = "";
		password = "";
		date = "";
		authenticated = false;
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

	@Override
	public long getId() {
		return Integer.parseInt(id);
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getRelatedUsername() {
		return relatedUsername;
	}
	
	public boolean getAuthenticated() {
		return authenticated;
	}
	
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
