package it4kids.web;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it4kids.dao.indatabase.quiz.AnswerDAO;
import it4kids.domain.quiz.Quiz;
import it4kids.domain.quiz.QuizAnswer;
import it4kids.domain.quiz.QuizEntry;
import it4kids.domain.quiz.UserAnswer;
import it4kids.service.quiz.QuizService;
import it4kids.service.quiz.UserAnswerService;

@Controller
@RequestMapping(value = "/answer")
public class UserAnswerController {

	@Autowired
	private UserAnswerService userService;
	@Autowired
	private QuizService quizService;
	@Autowired
	private AnswerDAO daoAnswer;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView listQuizes() {
		ModelAndView result = new ModelAndView("answer/selectQuiz");

		Collection<Quiz> quizes = quizService.listAll();
		result.addObject("quiz", new Quiz());
		result.addObject("quizList", quizes);

		return result;
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public ModelAndView startQuiz(Long id) {
		ModelAndView result = new ModelAndView("answer/listQuestion");

		Quiz quiz = quizService.get(id);
		QuizAnswer quizAnswer = new QuizAnswer();
		// quizEntry.setQuiz(quiz);
		result.addObject("quiz", quiz);
		result.addObject("answers", quizAnswer);

		return result;
	}
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public ModelAndView showResult() {
		ModelAndView result = new ModelAndView("answer/listResult");

		
		Collection<QuizAnswer> answer = userService.listAll();
		result.addObject("answers", answer);
		return result;

		
	}

}