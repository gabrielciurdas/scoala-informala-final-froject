
/**
 * Created by Gabi on 3/15/2017.
 */
package it4kids.configuration;

//<<<<<<< HEAD
import it4kids.dao.AccountDAO;
import it4kids.dao.UserDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.dao.indatabase.quiz.OptionDAO;
import it4kids.dao.indatabase.quiz.QuizDAO;
import it4kids.dao.indatabase.quiz.QuizEntryDAO;
import it4kids.dao.inmemory.login.IMAccountDAO;
import it4kids.dao.inmemory.quiz.IMOptionDAO;
import it4kids.dao.inmemory.quiz.IMQuizDAO;
import it4kids.dao.inmemory.quiz.IMQuizEntryDAO;
import it4kids.domain.login.UserLogin;
//>>>>>>> it4kids
import it4kids.service.login.AccountService;
import it4kids.service.login.UserLoginService;
import it4kids.service.login.UserService;
import it4kids.service.quiz.QuizEntryService;
import it4kids.service.quiz.QuizService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//=======

@Configuration
public class ApplicationConfiguration {

	// <<<<<<< HEAD
	@Bean
	public AccountService accountService1() {
		return new AccountService();
	}

	@Bean
	public UserService userService1() {
		return new UserService();
	}

	@Bean
	public UserDAO userDAO() {
		return new RegisteredUserDAO();
	}

	@Bean
	public QuizService quizService() {
		QuizService qs = new QuizService();

		qs.setQuizDao(quizDAO());
		return qs;
	}

	@Bean
	public QuizDAO quizDAO() {
		return new IMQuizDAO();
	}

	@Bean
	public QuizEntryService quizEntryServiceService() {
		QuizEntryService qes = new QuizEntryService();

		qes.setQuizEntryDao(quizEntryDAO());
		return qes;
	}

	@Bean
	public QuizEntryDAO quizEntryDAO() {
		return new IMQuizEntryDAO();
	}

	@Bean
	public OptionDAO optionDAO() {
		return new IMOptionDAO();
	}

	@Bean
	public AccountService accountService() {
		return new AccountService();
	}

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public UserLoginService userLoginService() {
		return new UserLoginService();
	}

	@Bean
	public AccountDAO<UserLogin> accountDAO() {
		return new IMAccountDAO<>();
	}

}
