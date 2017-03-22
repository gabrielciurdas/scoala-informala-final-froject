package it4kids.web;

import it4kids.domain.quiz.Quiz;

import it4kids.service.ValidationException;

import it4kids.service.quiz.QuizService;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("quiz/list");
		Collection<Quiz> quizes = quizService.listAll();
		result.addObject("quizList", quizes);

		return result;

	}

	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("quiz/add");
		result.addObject("quiz", new Quiz());
		return result;
	}

	@RequestMapping("/save")
	public ModelAndView saveQuiz(Quiz quiz, BindingResult bindingresult) {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
				quizService.save(quiz);
				result = new ModelAndView();
				result.setView(new RedirectView("/quiz"));
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				result = new ModelAndView("quiz/add");
				result.addObject("error", e.getMessage());
				result.addObject("quiz", quiz);
			}
		} else {
			List<FieldError> errors = bindingresult.getFieldErrors();
			StringBuilder sb = new StringBuilder();
			for (FieldError fieldError : errors) {
				sb.append(fieldError.getField());
				sb.append("-");
				sb.append(fieldError.getCode());
				sb.append("<br>");
			}

			result = new ModelAndView("quiz/add");
			result.addObject("error", sb.toString());
			result.addObject("quiz", quiz);
		}
		return result;
	}

}