
/**
 * Created by Gabi on 3/15/2017.
 */
package it4kids.configuration;

<<<<<<< HEAD
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it4kids.dao.indatabase.login.ChildAccountDAO;
import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;
import it4kids.dao.indatabase.login.ParentAccountDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.dao.indatabase.login.TeacherAccountDAO;
import it4kids.service.login.AdminService;
import it4kids.service.login.ChildService;
import it4kids.service.login.ParentService;
import it4kids.service.login.TeacherService;
=======
//<<<<<<< HEAD
//<<<<<<< HEAD
import it4kids.dao.AccountDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.dao.indatabase.quiz.OptionDAO;
import it4kids.dao.indatabase.quiz.QuizDAO;
import it4kids.dao.indatabase.quiz.QuizEntryDAO;
import it4kids.dao.inmemory.login.IMAccountDAO;
import it4kids.dao.inmemory.quiz.IMOptionDAO;
import it4kids.dao.inmemory.quiz.IMQuizDAO;
import it4kids.dao.inmemory.quiz.IMQuizEntryDAO;
import it4kids.domain.login.UserLogin;
//>>>>>>> origin/Gabi
import it4kids.service.login.AccountService;
import it4kids.service.login.UserLoginService;
>>>>>>> origin/Catalin
import it4kids.service.login.UserService;
import it4kids.service.quiz.QuizService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//>>>>>>> it4kids
//=======
//=======

@Configuration
public class ApplicationConfiguration {
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/it4kids";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "aNewPa55w0rd";

//<<<<<<< HEAD
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
  /*  @EnableRedisHttpSession   
    public class Config {   //Spring alternative to HttpSession from Tomcat

        @Bean
        public LettuceConnectionFactory connectionFactory() {
            return new LettuceConnectionFactory();
        }
    }*/
	// =======
//=======
//>>>>>>> origin/Gabi
	@Bean
	public FilterRegistrationBean securityFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(createSecurityFilter());
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		registration.addUrlPatterns("/*");
		return registration;
	}

	@Bean
	public RegisteredUserDAO registeredUserDAO() {
		return new RegisteredUserDAO();
	}
	
	@Bean
	public ParentAccountDAO parentAccountDAO() {
		return new ParentAccountDAO();
	}
	
<<<<<<< HEAD
=======
	/*@Bean
	@Qualifier("UserLoginService")
	public UserDAO userDAO() {
		return new IMUserDAO();
	}*/

//<<<<<<< HEAD
	// @Bean
	// public RegisteredUserDAO registeredUserDAO() {
	// return new RegisteredUserDAO();
	// }
//=======
>>>>>>> origin/Catalin
	@Bean
	public ChildAccountDAO ChildAccountDAO() {
		return new ChildAccountDAO();
	}
	
	@Bean
	public TeacherAccountDAO TeacherAccountDAO() {
		return new TeacherAccountDAO();
	}
	
	@Bean 
	public AdminService adminService() {
		return new AdminService();
	}
	
	@Bean 
	public UserService userService() {
		return new UserService();
	}
	
	@Bean 
	public TeacherService teacherService() {
		return new TeacherService();
	}
	
	@Bean ParentService parentService() {
		return new ParentService();
	}
	
	@Bean 
	public ChildService chilldService() {
		return new ChildService();
	}
//>>>>>>> origin/Gabi
	
	@Bean
	public SecurityFilter createSecurityFilter() {
		return new SecurityFilter();
	}

<<<<<<< HEAD
	@Bean
	public BasicDataSource dataSource() {

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(DB_CONNECTION);
		basicDataSource.setUsername(DB_USER);
		basicDataSource.setPassword(DB_PASSWORD);

		return basicDataSource;
	}
	@Bean
	public JdbcTemplateUserDAO jdbcTemplateDAO() {
		return new JdbcTemplateUserDAO(dataSource());
	}
=======
	/*
	 * @EnableRedisHttpSession public class Config { //Spring alternative to
	 * HttpSession from Tomcat
	 * 
	 * @Bean public LettuceConnectionFactory connectionFactory() { return new
	 * LettuceConnectionFactory(); } }
	 */
//<<<<<<< HEAD
	// >>>>>>> it4kids
//=======
//>>>>>>> origin/Gabi
>>>>>>> origin/Catalin
}
