package it4kids.domain;

public enum AccountType {
	ADMIN("Admin"),
	TEACHER("Teacher"),
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
