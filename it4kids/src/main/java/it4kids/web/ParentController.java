package it4kids.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it4kids.service.login.ParentService;
import it4kids.service.login.UserService;


@Controller
@RequestMapping("/parent")
public class ParentController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ParentService parentService;
/*	@Autowired
	RegisteredUserDAO userService;*/
	
	@RequestMapping("/parent")
	public ModelAndView parentToMainView() {
		ModelAndView result = new ModelAndView("it4kids/parent/parent");
		
		return result;
	}
	
	@RequestMapping("/")
	public ModelAndView returnToParentMainView() {
		ModelAndView result = new ModelAndView("it4kids/parent/parent");
		
		return result;
	}
}
