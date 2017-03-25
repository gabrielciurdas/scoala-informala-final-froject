package it4kids.web;

import it4kids.domain.quiz.QuizEntry;
import it4kids.service.ValidationException;
import it4kids.service.quiz.QuizService;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("")
public class QuizController {

	@Autowired
	private QuizService quizService;


	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("quizz/index");

		Collection<Quiz> quizes = quizService.listAll();
		result.addObject("quiz", new Quiz());
		result.addObject("quizList", quizes);

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView saveQuiz(Quiz quiz, BindingResult bindingresult)
			throws ValidationException {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
				quizService.save(quiz);
				result = new ModelAndView();
				result.setView(new RedirectView("/index"));
			} catch (ValidationException e) {
				result = new ModelAndView("quizz/index");
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

			result = new ModelAndView("quizz/index");
			result.addObject("error", sb.toString());
			result.addObject("quiz", quiz);
		}
		return result;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(Long id) {
		quizService.delete(id);
		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("/index");
		result.setView(redirect);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView result = new ModelAndView("quizz/edit");

		Quiz quiz = quizService.get(id);
		result.addObject("quiz", quiz);
		QuizEntry quizEntry = new QuizEntry();
		quizEntry.getOptions().add(new Option());
		quizEntry.getOptions().add(new Option());
		quizEntry.getOptions().add(new Option());
		quizEntry.getOptions().add(new Option());
		quizEntry.setQuiz(quiz);
		result.addObject("quizEntry", quizEntry);

		return result;
	}

	@RequestMapping(value = "/edit/add", method = RequestMethod.POST)
	public ModelAndView saveQuizEntry(Long quizId, Long quizEntryId,
			Integer expected,
			QuizEntry quizEntry, Option option, BindingResult bindingresult)
			throws ValidationException {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
				QuizEntry qe = quizService.getQuizEntry(quizEntryId);
				option.setQuizEntry(qe);
				List<Option> options = quizEntry.getOptions();
				int i = 1;
				for (Option option2 : options) {
					option2.setCorrect(i == expected);
					option2.setQuizEntry(quizEntry);
					quizService.saveOption(option2);
					i++;
				}
				Quiz quiz = quizService.get(quizId);
				quizEntry.setQuiz(quiz);
				quizService.saveQuizEntry(quizEntry);
				quiz.getQuestions().add(quizEntry);
				quizService.save(quiz);
				result = new ModelAndView();
				result.addObject("id", quiz.getId());
				result.setView(new RedirectView("/edit"));
			} catch (ValidationException e) {
				result = new ModelAndView("quizz/edit");
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

			result = new ModelAndView("quizz/edit");
			result.addObject("error", sb.toString());
			result.addObject("quizEntry", quizEntry);
		}
		return result;
	}

	@RequestMapping("/deleteQuestion")
	public ModelAndView deleteQuizEntry(Long quizEntryId, Long id)
			throws ValidationException {

		QuizEntry qz = quizService.getQuizEntry(quizEntryId);

		Quiz quiz = quizService.get(id);
		Iterator<QuizEntry> quizList = quiz.getQuestions().iterator();
		while (quizList.hasNext()) {
			qz = quizList.next();
			if (qz.getId() == quizEntryId) {
				quizList.remove();
			}
		}
		quizService.save(quiz);
		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("/edit?id="
				+ qz.getQuiz().getId());
		result.setView(redirect);
		return result;
	}

}