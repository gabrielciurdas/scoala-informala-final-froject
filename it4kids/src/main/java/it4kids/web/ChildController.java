package it4kids.web;

import it4kids.domain.quiz.Quiz;
import it4kids.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/child")
public class ChildController {
	
	@Autowired
	private QuizService quizService;
	
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
