package it4kids.domain.login;

public enum AccountType {
	ADMIN("Admin"),
	TEACHER("Teacher"),
	PRIMARY_PARENT("Primary_Parent"),
	PARENT("Parent"),
	CHILD("Child");
	
	String type = "";
	
	private AccountType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
} 
