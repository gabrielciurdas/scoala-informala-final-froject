package it4kids.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it4kids.dao.indatabase.login.RegisteredUserDAO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	RegisteredUserDAO userService;
	
	@RequestMapping("/admin")
	public ModelAndView returnToMainView() {
		ModelAndView result = new ModelAndView("admin/admin");
		result.addObject("userLogin", userService.getUserLogin());
		return result;
	}
	
	@RequestMapping("/adminRegister")
	public ModelAndView parentRegisterView() {
		ModelAndView result = new ModelAndView("admin/adminRegister");
		result.addObject("userLogin", userService.getUserLogin());
		return result;
	}
}
