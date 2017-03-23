package it4kids.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/parent")
public class ParentController {

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
	
	@RequestMapping("/parentRegister")
	public ModelAndView parentRegisterView() {
		ModelAndView result = new ModelAndView("it4kids/parent/parentRegister");
		
		return result;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("it4kids/login");
		return result;
	}
}
