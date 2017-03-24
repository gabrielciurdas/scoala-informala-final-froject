package it4kids.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it4kids.service.login.ParentService;
import it4kids.service.login.UserService;

@Controller
@RequestMapping("/primary_parent")
public class PrimaryParentController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ParentService parentService;
/*	@Autowired
	RegisteredUserDAO userService;*/
	
	@RequestMapping("/parent")
	public ModelAndView parentToMainView() {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/parent");
		
		return result;
	}
	
	@RequestMapping("/")
	public ModelAndView returnToParentMainView() {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/parent");
		
		return result;
	}
	
	@RequestMapping("/parentRegister")
	public ModelAndView parentRegisterView() {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/parentRegister");
		
		return result;
	}
	
	@RequestMapping("/parentRegister/register")
	public ModelAndView onRegister(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/parentRegister");
		try {
			System.out.println("trying to register");
			userService.add(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}