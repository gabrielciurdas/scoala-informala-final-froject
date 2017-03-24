package it4kids.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it4kids.service.login.ChildService;

@Controller
@RequestMapping("/child")
public class ChildController {
	
	@Autowired
	private ChildService childService;
	
	@RequestMapping("/child")
	public ModelAndView childMainView() {
		ModelAndView result = new ModelAndView("it4kids/child/child");
		
		return result;
	}
	
	@RequestMapping("/")
	public ModelAndView returnToChildMainView() {
		ModelAndView result = new ModelAndView("it4kids/child/child");
		
		return result;
	}
	
	@RequestMapping("/quiz")
	public ModelAndView quizView() {
		ModelAndView result = new ModelAndView("it4kids/child/quiz");
		
		return result;
	}
}
