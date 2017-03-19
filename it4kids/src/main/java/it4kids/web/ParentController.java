package it4kids.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it4kids.dao.indatabase.login.RegisteredUserDAO;

@Controller
@RequestMapping("/parent")
public class ParentController {

	@Autowired
	RegisteredUserDAO userService;
	
	@RequestMapping("/parent")
	public ModelAndView returnToMainView() {
		ModelAndView result = new ModelAndView("parent/parent");
		result.addObject("userLogin", userService.getUserLogin());
		return result;
	}
	
	@RequestMapping("/parentRegister")
	public ModelAndView parentRegisterView() {
		ModelAndView result = new ModelAndView("parent/parentRegister");
		result.addObject("userLogin", userService.getUserLogin());
		return result;
	}
	
}
