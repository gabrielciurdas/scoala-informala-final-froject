package it4kids.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it4kids.domain.login.User;
import it4kids.service.ValidationException;
import it4kids.service.login.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserService userService;
	
	@Test(expected = ValidationException.class)
	public void whenAllFieldsAreEmpty_ExceptionIsThrown() throws ValidationException {

		User user = new User();
		user.setFirstName("");
		user.setLastName("");
		user.setAccountType("");
		user.setEmail("");
		user.setUserName("");
		user.setPassword("");
		user.setPasswordConfirm("");
		
		userService.validate(user);
	}

	@Test(expected = ValidationException.class)
	public void whenEmptyFirstNameIsEntered_ExceptionIsThrown() throws ValidationException {

		User user = new User();
		user.setFirstName("");
		user.setLastName("");
		user.setAccountType("");
		user.setEmail("");
		user.setUserName("");
		user.setPassword("");
		user.setPasswordConfirm("");
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenFirstNameContainsNonAlphabeticalCharacters_ExceptionIsThrown() throws ValidationException {
		String firstName = "#@firstName";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "userName";
		String password = "password";
		String passwordConfirm = "password";

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenFirstNameEnteredIsLessThanThreeCharactersLength_ExceptionIsThrown() throws ValidationException {
		String firstName = "fn";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "userName";
		String password = "password";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	

	@Test(expected = ValidationException.class)
	public void whenLastNameIsEmpty_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "userName";
		String password = "password";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenLastNameContainsNonAlphabeticalCharacters_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "$%lastName";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "userName";
		String password = "password";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenLastNameEnteredIsLessThanThreeCharactersLength_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "ln";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "userName";
		String password = "password";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenEmptyAccountTypeIsEntered_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "lastName";
		String accountType = "";
		String email = "email@yahoo.com";
		String userName = "userName";
		String password = "password";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenEmptyEmailIsEntered_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "";
		String userName = "userName";
		String password = "password";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenInvalidEmailFormatIsEntered_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "myEmail.com";
		String userName = "userName";
		String password = "password";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenEmptyUserNameIsEntered_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "";
		String password = "password";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenNonAlphaNumericUserNameIsEntered_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "userName#$";
		String password = "password";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenUserNameEnteredIsLessThanThreeCharactersInLength_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "un";
		String password = "password";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenEmptyPasswordIsEntered_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "userName";
		String password = "";
		String passwordConfirm = "password";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenNonAlphaNumericPasswordIsEntered_ExceptionIsThrown() throws ValidationException {
		String firstName = "firstName";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "userName";
		String password = "pa$$word";
		String passwordConfirm = "pa$$word";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenPasswordEnteredIsLessThanThreeCharactersInLength_ExceptionIsThrown() throws ValidationException {
		String firstName = "";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "userName";
		String password = "pw";
		String passwordConfirm = "pw";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenConfirmedPasswordDiffersFromPassword_ExceptionIsThrown() throws ValidationException {
		String firstName = "";
		String lastName = "lastName";
		String accountType = "ADMIN";
		String email = "email@yahoo.com";
		String userName = "userName";
		String password = "password";
		String passwordConfirm = "p4ssw0rd";
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAccountType(accountType);
		user.setEmail(email);
		user.setUserName(userName);
		user.setPassword(password);
		user.setPasswordConfirm(passwordConfirm);
		
		userService.validate(user);
	}
}
