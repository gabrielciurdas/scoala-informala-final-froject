package it4kids.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it4kids.domain.quiz.Quiz;
import it4kids.service.login.ChildService;
import it4kids.service.quiz.QuizService;

@Controller
@RequestMapping("/child")
public class ChildController {
	
	@Autowired
	private QuizService quizService;
	
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
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView listQuizes() {
		ModelAndView result = new ModelAndView("it4kids/answer/selectQuiz");

		Collection<Quiz> quizes = quizService.listAll();
		result.addObject("quiz", new Quiz());
		result.addObject("quizList", quizes);

		return result;
	}
}
