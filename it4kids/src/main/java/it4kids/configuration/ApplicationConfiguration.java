
/**
 * Created by Gabi on 3/15/2017.
 */
package it4kids.configuration;

import it4kids.dao.UserDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.dao.indatabase.quiz.QuizDAO;
import it4kids.dao.inmemory.quiz.IMQuizDAO;
import it4kids.service.login.AccountService;
import it4kids.service.login.UserService;
import it4kids.service.quiz.QuizService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AccountService accountService() {
        return new AccountService();
    }
    
    @Bean
    public UserService userService() {
    	return new UserService();
    }

    @Bean
    public UserDAO userDAO() {
        return new RegisteredUserDAO();
    }

	@Bean
	public QuizService employeeService() {
		QuizService ems = new QuizService();

		ems.setDao(quizDAO());
		return ems;
	}

	@Bean
	public QuizDAO quizDAO() {
		return new IMQuizDAO();
	}

  /*  @EnableRedisHttpSession   
    public class Config {   //Spring alternative to HttpSession from Tomcat

        @Bean
        public LettuceConnectionFactory connectionFactory() {
            return new LettuceConnectionFactory();
        }
    }*/
}
