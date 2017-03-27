package it4kids.web;

import it4kids.domain.quiz.Option;
import it4kids.domain.quiz.Quiz;
import it4kids.domain.quiz.QuizEntry;
import it4kids.service.ValidationException;
import it4kids.service.quiz.QuizService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

	private static final int OPTION_ONE = 1;
	private static final int OPTION_TWO = 2;
	private static final int OPTION_THREE = 3;
	private static final int OPTION_FOUR = 4;

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
<<<<<<< HEAD
	public ModelAndView delete(Long quizId) {
		quizService.delete(quizId);
=======
	public ModelAndView delete(Long id) {
		quizService.delete(id);
>>>>>>> parent of e07083c... Reverse commit #2
		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("/index");
		result.setView(redirect);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
<<<<<<< HEAD
	public ModelAndView edit(Long quizId) {
		ModelAndView result = new ModelAndView("quizz/edit");

		Quiz quiz = quizService.get(quizId);
		QuizEntry quizEntry = new QuizEntry();
		OptionsWrapper qef = new OptionsWrapper();
		quizEntry.setQuizId(quizId);
		result.addObject("quiz", quiz);
		result.addObject("quizEntry", quizEntry);
		result.addObject("optionsWrapper", qef);
=======
	public ModelAndView edit(Long id) {
		ModelAndView result = new ModelAndView("quizz/edit");

		Quiz quiz = quizService.get(id);
		QuizEntry quizEntry = new QuizEntry();
		QuizEntryForm qef = new QuizEntryForm();
		quizEntry.setQuiz(quiz);
		result.addObject("quiz", quiz);
		result.addObject("quizEntry", quizEntry);
		result.addObject("option", qef);
>>>>>>> parent of e07083c... Reverse commit #2

		return result;
	}

<<<<<<< HEAD
	@RequestMapping("/addQuestion")
	public ModelAndView addQuizEntry(Long quizId) {
		ModelAndView result = new ModelAndView("quizz/addQuestion");
		Quiz quiz = quizService.get(quizId);
		QuizEntry quizEntry = new QuizEntry();
		OptionsWrapper qef = new OptionsWrapper();
		quizEntry.setQuizId(quizId);
		result.addObject("quiz", quiz);
		result.addObject("quizEntry", quizEntry);
		result.addObject("optionsWrapper", qef);
		return result;
	}

	@RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
	public ModelAndView saveQuizEntry(Long quizId, Long quizEntryId,
			QuizEntry quizEntry,
			OptionsWrapper qef, BindingResult bindingresult)
=======
	@RequestMapping(value = "/edit/add", method = RequestMethod.POST)
	public ModelAndView saveQuizEntry(Long quizId, QuizEntry quizEntry,
			QuizEntryForm qef, BindingResult bindingresult)
>>>>>>> parent of e07083c... Reverse commit #2
			throws ValidationException {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
<<<<<<< HEAD
				Quiz quiz = quizService.get(quizId);
				quizEntry.setQuizId(quizId);
				quizService.saveQuizEntry(quizEntry);

				List<Option> options = saveOptions(quizEntryId, qef);
=======

				Quiz quiz = quizService.get(quizId);
				quizEntry.setQuiz(quiz);
				quizService.saveQuizEntry(quizEntry);

				List<Option> options = saveOptions(quizEntry, qef);
>>>>>>> parent of e07083c... Reverse commit #2
				// doar pt IMDAO -- start
				quizEntry.setOptions(options);
				quizService.saveQuizEntry(quizEntry);
				// doar pt IMDAO -- end
				quiz.getQuestions().add(quizEntry);
				quizService.save(quiz);
				result = new ModelAndView();
<<<<<<< HEAD
				result.addObject("quizId", quiz.getId());
				result.setView(new RedirectView("/edit?quizId=" + quizId));
			} catch (ValidationException e) {
				result = new ModelAndView("quizz/addQuestion");
=======
				result.addObject("id", quiz.getId());
				result.setView(new RedirectView("/edit"));
			} catch (ValidationException e) {
				result = new ModelAndView("quizz/edit");
>>>>>>> parent of e07083c... Reverse commit #2
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

<<<<<<< HEAD
			result = new ModelAndView("quizz/addQuestion");
=======
			result = new ModelAndView("quizz/edit");
>>>>>>> parent of e07083c... Reverse commit #2
			result.addObject("error", sb.toString());
			result.addObject("quizEntry", quizEntry);
		}
		return result;
	}

<<<<<<< HEAD
	@RequestMapping("/deleteQuestion")
	public ModelAndView deleteQuizEntry(Long quizEntryId, Long quizId)
			throws ValidationException {

		QuizEntry qz = quizService.getQuizEntry(quizEntryId);

		Quiz quiz = quizService.get(quizId);
		Iterator<QuizEntry> quizList = quiz.getQuestions().iterator();
		while (quizList.hasNext()) {
			qz = quizList.next();
			if (qz.getId() == quizEntryId) {
				quizList.remove();
			}
		}
		quizService.save(quiz);
		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("/edit?quizId="
				+ qz.getQuizId());
		result.setView(redirect);
		return result;
	}

	@RequestMapping("/editQuestion")
	public ModelAndView editQuestion(Long quizId, Long quizEntryId) {
		ModelAndView result = new ModelAndView("quizz/addQuestion");
		QuizEntry qz = quizService.getQuizEntry(quizEntryId);

		Quiz quiz = quizService.get(quizId);

		if (qz != null) {
			OptionsWrapper qef = this.readOptions(qz.getOptions());
			result.addObject("quiz", quiz);
			result.addObject("quizEntry", qz);
			result.addObject("optionsWrapper", qef);
		} else {
			result = new ModelAndView();
			RedirectView redirect = new RedirectView("/edit?quizId=" + quizId);
			result.setView(redirect);
		}
		return result;
	}

	private List<Option> saveOptions(Long quizEntryId, OptionsWrapper qef)
=======
	private List<Option> saveOptions(QuizEntry quizEntry, QuizEntryForm qef)
>>>>>>> parent of e07083c... Reverse commit #2
			throws ValidationException {
		List<Option> options = new ArrayList<Option>();
		String textOption1 = qef.getTextOption1();
		if (textOption1 != null && textOption1.trim().length() > 0) {
			Option o1 = new Option();
			o1.setTextOption(textOption1);
			o1.setCorrect(OPTION_ONE == qef.getExpected());
<<<<<<< HEAD
			o1.setQuizEntryId(quizEntryId);
=======
			o1.setQuizEntry(quizEntry);
>>>>>>> parent of e07083c... Reverse commit #2
			quizService.saveOption(o1);
			options.add(o1);
		}

		String textOption2 = qef.getTextOption2();
		if (textOption2 != null && textOption2.trim().length() > 0) {
			Option o2 = new Option();
			o2.setTextOption(textOption2);
			o2.setCorrect(OPTION_TWO == qef.getExpected());
<<<<<<< HEAD
			o2.setQuizEntryId(quizEntryId);
=======
			o2.setQuizEntry(quizEntry);
>>>>>>> parent of e07083c... Reverse commit #2
			quizService.saveOption(o2);
			options.add(o2);
		}

		String textOption3 = qef.getTextOption3();
		if (textOption3 != null && textOption3.trim().length() > 0) {
			Option o3 = new Option();
			o3.setTextOption(textOption3);
			o3.setCorrect(OPTION_THREE == qef.getExpected());
<<<<<<< HEAD
			o3.setQuizEntryId(quizEntryId);
=======
			o3.setQuizEntry(quizEntry);
>>>>>>> parent of e07083c... Reverse commit #2
			quizService.saveOption(o3);
			options.add(o3);
		}

		String textOption4 = qef.getTextOption4();
		if (textOption4 != null && textOption4.trim().length() > 0) {
			Option o4 = new Option();
			o4.setTextOption(textOption4);
			o4.setCorrect(OPTION_FOUR == qef.getExpected());
<<<<<<< HEAD
			o4.setQuizEntryId(quizEntryId);
=======
			o4.setQuizEntry(quizEntry);
>>>>>>> parent of e07083c... Reverse commit #2
			quizService.saveOption(o4);
			options.add(o4);
		}
		return options;
	}

<<<<<<< HEAD
	private OptionsWrapper readOptions(List<Option> options) {
		OptionsWrapper result = new OptionsWrapper();
		for (int i = 0; i < options.size(); i++) {
			Option currentOption = options.get(i);
			if (currentOption.getCorrect()) {
				result.setExpected(i + 1);
			}
		}

		Option optionOne = options.get(0);
		String textOption1;
		textOption1 = optionOne.getTextOption();
		result.setTextOption1(textOption1);

		Option optionTwo = options.get(1);
		String textOption2;
		textOption2 = optionTwo.getTextOption();
		result.setTextOption2(textOption2);

		Option optionThree = options.get(2);
		String textOption3;
		textOption3 = optionThree.getTextOption();
		result.setTextOption3(textOption3);

		Option optionFour = options.get(3);
		String textOption4;
		textOption4 = optionFour.getTextOption();
		result.setTextOption4(textOption4);

=======
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
>>>>>>> parent of e07083c... Reverse commit #2
		return result;
	}

}