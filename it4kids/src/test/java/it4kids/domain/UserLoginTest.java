package it4kids.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it4kids.service.ValidationException;
import it4kids.service.login.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserLoginTest {

	@Autowired
	private LoginService loginService;

	@Test(expected = ValidationException.class)
	public void whenEmptyUserNameIsEntered_ExceptionIsThrown() throws ValidationException {
		String userName = "";
		String password = "password";

		UserLogin user = new UserLogin();
		user.setUserName(userName);
		user.setPassword(password);
	}
	
	@Test(expected = ValidationException.class)
	public void whenEmptyPasswordIsEntered_ExceptionIsThrown() throws ValidationException {
		String userName = "gabi";
		String password = "";

		UserLogin user = new UserLogin();
		user.setUserName(userName);
		user.setPassword(password);
	}
	
	@Test(expected = ValidationException.class)
	public void whenEmptyUserNameAndPasswordIsEntered_ExceptionIsThrown() throws ValidationException {
		String userName = "";
		String password = "";

		UserLogin user = new UserLogin();
		user.setUserName(userName);
		user.setPassword(password);
	}
	
	@Test(expected = ValidationException.class)
	public void whenInValidCredentialsAreEntered_ExceptionIsThrown() throws ValidationException {
		String userName = "someoneUnregistered";
		String password = "admin";

		UserLogin user = new UserLogin();
		user.setUserName(userName);
		user.setPassword(password);

		loginService.save(user);
	}
	
	@Test
	public void whenValidCredentialsAreEntered_NoExceptionIsThrown() throws ValidationException {
		String userName = "admin";
		String password = "admin";

		UserLogin user = new UserLogin();
		user.setUserName(userName);
		user.setPassword(password);

		loginService.save(user);
	}
}
