package it4kids.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it4kids.domain.UserLogin;
import it4kids.service.SecurityService;
import it4kids.service.login.UserService;

@Component
public class SecurityFilter implements Filter {

	@Autowired
	private SecurityService securityService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		UserLogin userLogin = (UserLogin) ((HttpServletRequest) request).getSession().getAttribute("currentUser");

		securityService.setCurrentUser(userLogin);

		String url = ((HttpServletRequest) request).getRequestURL().toString();

		if (url.contains("admin")) {
			if (!userLogin.getAccountType().equalsIgnoreCase("admin")) {
				HttpServletResponse servletResponse = (HttpServletResponse) response;
				servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				servletResponse.setHeader("Location", "/login");
				return;
			}
		}
		if (url.contains("primary_parent")) {
			if (!userLogin.getAccountType().equalsIgnoreCase("primary_parent")) {
				HttpServletResponse servletResponse = (HttpServletResponse) response;
				servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				servletResponse.setHeader("Location", "/login");
				return;
			}

		} if (url.contains("teacher")) {
			if (!userLogin.getAccountType().equalsIgnoreCase("teacher")) {
				HttpServletResponse servletResponse = (HttpServletResponse) response;
				servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				servletResponse.setHeader("Location", "/login");
				return;
			}

		} if (url.contains("child")) {
			if (!userLogin.getAccountType().equalsIgnoreCase("child")) {
				HttpServletResponse servletResponse = (HttpServletResponse) response;
				servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				servletResponse.setHeader("Location", "/login");
				return;
			}

		} if (url.contains("selectQuiz")) {
				if (!userLogin.getAccountType().equalsIgnoreCase("child")) {
					HttpServletResponse servletResponse = (HttpServletResponse) response;
					servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					servletResponse.setHeader("Location", "/login");
					return;
				}

		} if (url.contains("parent")) {
			  if(userLogin.getAccountType().equalsIgnoreCase("primary_parent")) {
				  LOGGER.info("Current user: " + (userLogin != null ? userLogin.getUserName() : null));
					chain.doFilter(request, response);
					return;
					
			} else if (!userLogin.getAccountType().equalsIgnoreCase("parent")) {
				HttpServletResponse servletResponse = (HttpServletResponse) response;
				servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				servletResponse.setHeader("Location", "/login");
				return;
			}
		}

		LOGGER.info("Current user: " + (userLogin != null ? userLogin.getUserName() : null));

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
