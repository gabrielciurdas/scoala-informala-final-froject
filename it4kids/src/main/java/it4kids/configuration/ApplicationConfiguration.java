
/**
 * Created by Gabriel Ciurdas on 3/15/2017.
 */
package it4kids.configuration;

import java.util.Collection;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it4kids.dao.ConnectionToDB;
import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;
import it4kids.dao.indatabase.quiz.AnswerDAO;
import it4kids.dao.indatabase.quiz.OptionDAO;
import it4kids.dao.indatabase.quiz.QuizDAO;
import it4kids.dao.indatabase.quiz.QuizEntryDAO;
import it4kids.dao.inmemory.quiz.IMOptionDAO;
import it4kids.dao.inmemory.quiz.IMQuizDAO;
import it4kids.dao.inmemory.quiz.IMQuizEntryDAO;
import it4kids.domain.UserLogin;
import it4kids.domain.quiz.QuizAnswer;
import it4kids.service.login.AdminService;
import it4kids.service.login.ChildService;
import it4kids.service.login.ParentService;
import it4kids.service.login.TeacherService;
import it4kids.service.login.UserService;
import it4kids.service.quiz.QuizService;
import it4kids.service.quiz.UserAnswerService;

@Configuration
public class ApplicationConfiguration {
	//Local deployment
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/it4kids";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "aNewPa55w0rd";
	
	@Bean
	public FilterRegistrationBean securityFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(createSecurityFilter());
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		registration.addUrlPatterns("/*");
		return registration;
	}

	/*@Bean
	public RegisteredUserDAO registeredUserDAO() {
		return new RegisteredUserDAO();
	}*/
	
	
/*	@Bean
	public ParentAccountDAO parentAccountDAO() {
		return new ParentAccountDAO();
	}
	*/
	/*@Bean
	public ChildAccountDAO ChildAccountDAO() {
		return new ChildAccountDAO();
	}*/
	
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
	
	@Bean
	public SecurityFilter createSecurityFilter() {
		return new SecurityFilter();
	}
	
	@Bean
	public QuizService quizService() {
		QuizService ems = new QuizService();

		ems.setQuizDao(quizDAO());
		return ems;
	}

	@Bean
	public UserAnswerService userAnswerService() {
		return new UserAnswerService();
	}
	@Bean
	public QuizDAO quizDAO() {
		return new IMQuizDAO();
	}
	
	@Bean
	public AnswerDAO answerDAO() {
		return new AnswerDAO() {
			
			@Override
			public QuizAnswer update(QuizAnswer model) {
				return null;
			}
			
			@Override
			public Collection<QuizAnswer> getAll() {
				return null;
			}
			
			@Override
			public QuizAnswer findById(Long id) {
				return null;
			}
			
			@Override
			public boolean delete(QuizAnswer model) {
				return false;
			}
		};
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
	
	@Bean
	public UserLogin userLogin() {
		return new UserLogin();
	}

	/*@Bean
	public JdbcTemplateUserDAO jdbcTemplateDAO() {
		return new JdbcTemplateUserDAO(dataSource());
	}*/
	
	@Bean
	public ConnectionToDB connectionToDB() {
		return new ConnectionToDB();
	}
	
	//Local deployment
	@Bean
	public BasicDataSource dataSource() {

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(DB_CONNECTION);
		basicDataSource.setUsername(DB_USER);
		basicDataSource.setPassword(DB_PASSWORD);

		return basicDataSource;
	}
	
	//Heroku deployment use only
	/*@Bean
	    public BasicDataSource dataSource() {
	        String dbUrl = System.getenv("JDBC_DATABASE_URL");
	        String username = System.getenv("JDBC_DATABASE_USERNAME");
	        String password = System.getenv("JDBC_DATABASE_PASSWORD");

	        BasicDataSource basicDataSource = new BasicDataSource();
	        basicDataSource.setUrl(dbUrl);
	        basicDataSource.setUsername(username);
	        basicDataSource.setPassword(password);

	        return basicDataSource;
	    }*/
}
