
/**
 * Created by Gabi on 3/15/2017.
 */
package it4kids.configuration;

//<<<<<<< HEAD
//<<<<<<< HEAD
import it4kids.dao.AccountDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.dao.indatabase.quiz.AnswerDAO;
import it4kids.dao.indatabase.quiz.OptionDAO;
import it4kids.dao.indatabase.quiz.QuizDAO;
import it4kids.dao.indatabase.quiz.QuizEntryDAO;
import it4kids.dao.inmemory.login.IMAccountDAO;
import it4kids.dao.inmemory.quiz.IMAnswerDAO;
import it4kids.dao.inmemory.quiz.IMOptionDAO;
import it4kids.dao.inmemory.quiz.IMQuizDAO;
import it4kids.dao.inmemory.quiz.IMQuizEntryDAO;
import it4kids.domain.login.UserLogin;
//>>>>>>> origin/Gabi
import it4kids.service.login.AccountService;
import it4kids.service.login.UserLoginService;
import it4kids.service.login.UserService;
import it4kids.service.quiz.QuizService;
import it4kids.service.quiz.UserAnswerService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//>>>>>>> it4kids
//=======
//=======

@Configuration
public class ApplicationConfiguration {

	// <<<<<<< HEAD
	// <<<<<<< HEAD

	// de ce nu asa??? :
	// @Bean
	// AccountService accountService;
	// dupa se poate apela direct cu "this.accountService"
	@Bean
	public AccountService accountService1() {
		return new AccountService();
	}

	@Bean
	public UserService userService1() {
		return new UserService();
	}

	// @Bean
	// public UserDAO userDAO() {
	// return new RegisteredUserDAO();
	// }

	@Bean
	public QuizService quizService() {
		QuizService ems = new QuizService();

		ems.setQuizDao(quizDAO());
		return ems;
	}

	@Bean
	public UserAnswerService userAnswerService() {
		UserAnswerService uas = new UserAnswerService();
		uas.setAnswerDAO(AnswerDAO());
		return uas;
	}

	@Bean
	public AnswerDAO AnswerDAO() {
		return new IMAnswerDAO();
	}

	@Bean
	public QuizDAO quizDAO() {
		return new IMQuizDAO();
	}

	@Bean
	public QuizService quizEntryServiceService() {
		QuizService ems = new QuizService();

		ems.setQuizEntryDao(quizEntryDAO());
		return ems;
	}

	@Bean
	public QuizEntryDAO quizEntryDAO() {
		return new IMQuizEntryDAO();
	}

	@Bean
	public QuizService employeeService() {
		QuizService ems = new QuizService();

		ems.setOptionDao(optionDAO());
		return ems;
	}

	@Bean
	public OptionDAO optionDAO() {
		return new IMOptionDAO();
	}

	/*
	 * @EnableRedisHttpSession public class Config { //Spring alternative to
	 * HttpSession from Tomcat
	 * 
	 * @Bean public LettuceConnectionFactory connectionFactory() { return new
	 * LettuceConnectionFactory(); } }
	 */
	// =======
	// =======
	// >>>>>>> origin/Gabi
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

	/*
	 * @Bean
	 * 
	 * @Qualifier("UserLoginService") public UserDAO userDAO() { return new
	 * IMUserDAO(); }
	 */

	// <<<<<<< HEAD
	// @Bean
	// public RegisteredUserDAO registeredUserDAO() {
	// return new RegisteredUserDAO();
	// }
	// =======
	@Bean
	public RegisteredUserDAO registeredUserDAO() {
		return new RegisteredUserDAO();
	}
	// >>>>>>> origin/Gabi

	@Bean
	public AccountDAO<UserLogin> accountDAO() {
		return new IMAccountDAO<>();
	}

	/*
	 * @EnableRedisHttpSession public class Config { //Spring alternative to
	 * HttpSession from Tomcat
	 * 
	 * @Bean public LettuceConnectionFactory connectionFactory() { return new
	 * LettuceConnectionFactory(); } }
	 */
	// <<<<<<< HEAD
	// >>>>>>> it4kids
	// =======
	// >>>>>>> origin/Gabi
}
