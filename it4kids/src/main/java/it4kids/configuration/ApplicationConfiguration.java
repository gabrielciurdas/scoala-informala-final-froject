
/**
 * Created by Gabi on 3/15/2017.
 */
package it4kids.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.service.login.AdminService;
import it4kids.service.login.ChildService;
import it4kids.service.login.ParentService;
import it4kids.service.login.TeacherService;
import it4kids.service.login.UserService;

@Configuration
public class ApplicationConfiguration {
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

	@Bean
	public RegisteredUserDAO registeredUserDAO() {
		return new RegisteredUserDAO();
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
	
	@Bean
	public SecurityFilter createSecurityFilter() {
		return new SecurityFilter();
	}

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
	/*
	@Bean
	public UserDAO userDAO() {
		return new JdbcTemplateUserDAO(dataSource());
	}*/
}
