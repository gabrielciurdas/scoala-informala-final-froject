package it4kids.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import it4kids.domain.quiz.Option;

import it4kids.domain.quiz.QuizEntry;
import it4kids.service.ValidationException;

import it4kids.service.quiz.QuizEntryService;
import it4kids.service.quiz.QuizService;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuizEntryService quizEntryService;

	@Autowired
	private QuizService quizService;

	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("questions/list");
		Collection<QuizEntry> questions = quizEntryService.listAll();
		result.addObject("questionList", questions);

		return result;

	}

	@RequestMapping("/add")
	public ModelAndView addQuestion() {
		ModelAndView result = new ModelAndView("questions/add");
		result.addObject("quizEntry", new QuizEntry());
		result.addObject("Option", new Option());

		return result;
	}

	@RequestMapping("/save")
	public ModelAndView saveQuestion(QuizEntry quizEntry, BindingResult bindingresult) {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
				quizEntryService.save(quizEntry);
				// quizService.save(quiz);
				result = new ModelAndView();
				result.setView(new RedirectView("/questions"));
			} catch (ValidationException e) {

				result = new ModelAndView("questions/add");
				result.addObject("error", e.getMessage());
				result.addObject("quizEntry", quizEntry);
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

			result = new ModelAndView("questions/add");
			result.addObject("error", sb.toString());
			result.addObject("quizEntry", quizEntry);
		}
		return result;
	}

	// @RequestMapping("/saveQuestionList")
	// public ModelAndView saveQuestionList() throws ValidationException {
	// ModelAndView result = null;
	//
	// quizEntryService.listAll();
	// result = new ModelAndView();
	// result.setView(new RedirectView("/quiz"));
	// return result;
	// }

	// @RequestMapping("/options/add")
	// public ModelAndView addOption() {
	// ModelAndView result = new ModelAndView("quizEntry/addOption");
	// result.addObject("option", new Option());
	// return result;
	// }

}
