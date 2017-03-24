package it4kids.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it4kids.domain.UserLogin;
import it4kids.service.SecurityService;

	@Component
	public class SecurityFilter implements Filter {

		@Autowired
		private SecurityService securityService;
		
		@Override
		public void destroy() {
			
		}

		@Override
		public void doFilter(ServletRequest request, 
				ServletResponse response, 
				FilterChain chain)
				throws IOException, ServletException {
			
			UserLogin userLogin =(UserLogin) ((HttpServletRequest)request).getSession().getAttribute("currentUser");
			
			securityService.
			setCurrentUser(userLogin);
			
			System.out.println("Thread name: " + Thread.currentThread().getName() +
					", current user: " + (userLogin != null ? userLogin.getUserName() : null));
			
			chain.doFilter(request, response);
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
			
		}
}
