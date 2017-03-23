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
import org.springframework.web.servlet.view.RedirectView;

import it4kids.service.login.AdminService;
import it4kids.service.login.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
/*
	@Autowired
	private AdminService adminService;*/
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/admin")
	public ModelAndView adminMainView() {
		ModelAndView result = new ModelAndView("it4kids/admin/admin");
		
		return result;
	}
	
	@RequestMapping("/")
	public ModelAndView returnToAdminMainView() {
		ModelAndView result = new ModelAndView("it4kids/admin/admin");
		
		return result;
	}
	
	@RequestMapping("/adminRegister")
	public ModelAndView adminRegisterView() {
		ModelAndView result = new ModelAndView("it4kids/admin/adminRegister");
		
		return result;
	}
	
	@RequestMapping("/adminRegister/register")
	public ModelAndView onRegister(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView result = new ModelAndView("it4kids/admin/adminRegister");
		try {
			System.out.println("trying to register");
			userService.add(req, resp);
			//result.setView(new RedirectView());
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("it4kids/login");
		return result;
	}
}
