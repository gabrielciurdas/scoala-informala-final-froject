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
public class ParentAccountTest {
	
	@Autowired
	private UserService userService;

	@Test(expected = ValidationException.class)
	public void whenParentUsernameIsEmpty_ExceptionIsThrown() throws ValidationException {
		String username = "";
		
		User user = new User();
		user.setUserName(username);
		
		userService.validateParentUserName(user);
	}
	
	@Test(expected = ValidationException.class)
	public void whenParentUsernameIsComposedOfNonAlphaNumericalCharacters_ExceptionIsThrown() throws ValidationException {
		String username = "#username%";
		
		User user = new User();
		user.setUserName(username);
		
		userService.validateParentUserName(user);
	}

}
