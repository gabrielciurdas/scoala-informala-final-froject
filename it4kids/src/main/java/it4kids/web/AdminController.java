package it4kids.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
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
	
	@RequestMapping("/logout")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("it4kids/login");
		return result;
	}
}
