
/**
 * Created by Gabi on 3/15/2017.
 */
package it4kids.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it4kids.dao.UserDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.service.login.AccountService;
import it4kids.service.login.UserService;

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

  /*  @EnableRedisHttpSession   
    public class Config {   //Spring alternative to HttpSession from Tomcat

        @Bean
        public LettuceConnectionFactory connectionFactory() {
            return new LettuceConnectionFactory();
        }
    }*/
}
