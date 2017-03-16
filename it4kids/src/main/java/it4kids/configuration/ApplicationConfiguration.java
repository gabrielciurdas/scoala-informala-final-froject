
/**
 * Created by Gabi on 3/15/2017.
 */
package it4kids.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import it4kids.dao.UserDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.service.login.AccountService;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AccountService accountService() {
        return new AccountService();
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
