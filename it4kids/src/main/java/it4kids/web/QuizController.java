package it4kids.web;

import it4kids.domain.quiz.Option;
import it4kids.domain.quiz.Quiz;
import it4kids.domain.quiz.QuizEntry;
import it4kids.service.ValidationException;
import it4kids.service.quiz.OptionService;
import it4kids.service.quiz.QuizEntryService;
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
	@Autowired
	private QuizEntryService quizEntryService;
	@Autowired
	private OptionService optionService;

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

	@RequestMapping("/questions")
	public ModelAndView listQuestions() {
		ModelAndView result = new ModelAndView("quiz/listQuizEntry");
		Collection<QuizEntry> quizEntry = quizEntryService.listAll();
		result.addObject("quizEntryList", quizEntry);
		return result;
	}

	@RequestMapping("/questions/add")
	public ModelAndView addQuestion() {
		ModelAndView result = new ModelAndView("quiz/addQuizEntry");
		result.addObject("quizEntry", new QuizEntry());
		return result;
	}

	@RequestMapping("questions/saveQuestion")
	public ModelAndView saveQuestion(QuizEntry quizEntry,
			BindingResult bindingresult) {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
				quizEntryService.save(quizEntry);
				result = new ModelAndView();
				result.setView(new RedirectView("/quiz/questions"));
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				result = new ModelAndView("quiz/addQuizEntry");
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

			result = new ModelAndView("quiz/addQuizEntry");
			result.addObject("error", sb.toString());
			result.addObject("quizEntry", quizEntry);
		}
		return result;
	}

	@RequestMapping("questions/saveQuestionList")
	public ModelAndView saveQuestionList() throws ValidationException {
		ModelAndView result = null;

		quizEntryService.listAll();
		result = new ModelAndView();
		result.setView(new RedirectView("/quiz"));
		return result;
	}

	@RequestMapping("/questions/options")
	public ModelAndView listOptions() {
		ModelAndView result = new ModelAndView("quiz/listOption");
		Collection<Option> option = optionService.listAll();
		result.addObject("optionList", option);
		return result;
	}

	@RequestMapping("/questions/options/add")
	public ModelAndView addOption() {
		ModelAndView result = new ModelAndView("quiz/addOption");
		result.addObject("option", new Option());
		return result;
	}

	@RequestMapping("questions/options/saveOption")
	public ModelAndView saveOption(Option option, BindingResult bindingresult) {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
				optionService.save(option);
				result = new ModelAndView();
				result.setView(new RedirectView("/quiz/questions/options"));
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				result = new ModelAndView("quiz/addOption");
				result.addObject("error", e.getMessage());
				result.addObject("option", option);
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

			result = new ModelAndView("quiz/addOption");
			result.addObject("error", sb.toString());
			result.addObject("option", option);
		}
		return result;
	}

	@RequestMapping("questions/options/saveOptionList")
	public ModelAndView saveOptionList() throws ValidationException {
		ModelAndView result = null;

		optionService.listAll();
		result = new ModelAndView();
		result.setView(new RedirectView("/quiz/questions"));
		return result;
	}
}