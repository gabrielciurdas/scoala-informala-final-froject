
/**
 * Created by Gabriel Ciurdas on 3/15/2017.
 */
package it4kids.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	
	@Bean
	public SecurityFilter createSecurityFilter() {
		return new SecurityFilter();
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
