package it4kids.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it4kids.dao.indatabase.quiz.AnswerDAO;
import it4kids.domain.quiz.Quiz;
import it4kids.domain.quiz.QuizEntry;
import it4kids.service.quiz.QuizService;
import it4kids.service.quiz.UserAnswerService;

@Controller
@RequestMapping("/answer")
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

	@RequestMapping(value = "/start/index")
	public ModelAndView startQuiz(Long id) {
		ModelAndView result = new ModelAndView("answer/listQuestions");

		Quiz quiz = quizService.get(id);
		QuizEntry quizEntry = new QuizEntry();
		QuizEntryForm qef = new QuizEntryForm();
		quizEntry.setQuiz(quiz);
		result.addObject("quiz", quiz);
		result.addObject("quizEntry", quizEntry);
		result.addObject("option", qef);

		return result;
	}

}
