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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/quiz")
public class QuizController {

	private static final int OPTION_ONE = 1;
	private static final int OPTION_TWO = 2;
	private static final int OPTION_THREE = 3;
	private static final int OPTION_FOUR = 4;

	@Autowired
	private QuizService quizService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("it4kids/quizz/index");

		Collection<Quiz> quizes = quizService.listAll();
		result.addObject("quiz", new Quiz());
		result.addObject("quizList", quizes);

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView saveQuiz(@Valid Quiz quiz, BindingResult bindingresult)
			throws ValidationException {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
				quizService.save(quiz);
				result = new ModelAndView();
				result.setView(new RedirectView("/quiz/index"));
			} catch (ValidationException e) {
				result = new ModelAndView("it4kids/quizz/index");
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

			result = new ModelAndView("it4kids/quizz/index");
			result.addObject("error", sb.toString());
			result.addObject("quiz", quiz);
		}
		return result;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(Long quizId) {
		quizService.delete(quizId);
		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("/quiz/index");
		result.setView(redirect);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long quizId) {
		ModelAndView result = new ModelAndView("it4kids/quizz/edit");

		Quiz quiz = quizService.get(quizId);
		QuizEntry quizEntry = new QuizEntry();
		OptionsWrapper qef = new OptionsWrapper();
		quizEntry.setQuizId(quizId);
		result.addObject("quiz", quiz);
		result.addObject("quizEntry", quizEntry);
		result.addObject("optionsWrapper", qef);

		return result;
	}

	@RequestMapping("/addQuestion")
	public ModelAndView addQuizEntry(Long quizId) {
		ModelAndView result = new ModelAndView("it4kids/quizz/addQuestion");
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
			@Valid QuizEntry quizEntry, OptionsWrapper qef,
			BindingResult bindingresult) throws ValidationException {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
				Quiz quiz = quizService.get(quizId);
				quizEntry.setId(quizEntryId);
				quizEntry.setQuizId(quizId);
				quizService.saveQuizEntry(quizEntry);

				List<Option> options = saveOptions(quizEntryId, qef);
				// doar pt IMDAO -- start
				quizEntry.setOptions(options);
				quizService.saveQuizEntry(quizEntry);
				// doar pt IMDAO -- end
				updateQuizQuestions(quizEntry, quiz);
				quizService.save(quiz);
				result = new ModelAndView();
				result.addObject("quizId", quiz.getId());
				result.setView(new RedirectView("/quiz/edit?quizId=" + quizId));
			} catch (ValidationException e) {
				result = new ModelAndView("it4kids/quizz/addQuestion");
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

			result = new ModelAndView("it4kids/quizz/addQuestion");
			result.addObject("error", sb.toString());
			result.addObject("quizEntry", quizEntry);
		}
		return result;
	}

	private void updateQuizQuestions(QuizEntry quizEntry, Quiz quiz) {

		List<QuizEntry> questions = quiz.getQuestions();

		for (Iterator<QuizEntry> it = questions.iterator(); it.hasNext();) {
			QuizEntry entry = it.next();

			if (entry.getId() == quizEntry.getId()) {
				it.remove();
			}
		}
		questions.add(quizEntry);

	}

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
		RedirectView redirect = new RedirectView("/quiz/edit?quizId="
				+ qz.getQuizId());
		result.setView(redirect);
		return result;
	}

	@RequestMapping("/editQuestion")
	public ModelAndView editQuestion(Long quizId, Long quizEntryId) {
		ModelAndView result = new ModelAndView("it4kids/quizz/addQuestion");
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
			throws ValidationException {
		List<Option> options = new ArrayList<Option>();
		String textOption1 = qef.getTextOption1();
		if (textOption1 != null && textOption1.trim().length() > 0) {
			Option o1 = new Option();
			o1.setTextOption(textOption1);
			o1.setCorrect(OPTION_ONE == qef.getExpected());
			o1.setQuizEntryId(quizEntryId);
			quizService.saveOption(o1);
			options.add(o1);
		}

		String textOption2 = qef.getTextOption2();
		if (textOption2 != null && textOption2.trim().length() > 0) {
			Option o2 = new Option();
			o2.setTextOption(textOption2);
			o2.setCorrect(OPTION_TWO == qef.getExpected());
			o2.setQuizEntryId(quizEntryId);
			quizService.saveOption(o2);
			options.add(o2);
		}

		String textOption3 = qef.getTextOption3();
		if (textOption3 != null && textOption3.trim().length() > 0) {
			Option o3 = new Option();
			o3.setTextOption(textOption3);
			o3.setCorrect(OPTION_THREE == qef.getExpected());
			o3.setQuizEntryId(quizEntryId);
			quizService.saveOption(o3);
			options.add(o3);
		}

		String textOption4 = qef.getTextOption4();
		if (textOption4 != null && textOption4.trim().length() > 0) {
			Option o4 = new Option();
			o4.setTextOption(textOption4);
			o4.setCorrect(OPTION_FOUR == qef.getExpected());
			o4.setQuizEntryId(quizEntryId);
			quizService.saveOption(o4);
			options.add(o4);
		}
		return options;
	}

	private OptionsWrapper readOptions(List<Option> options) {
		OptionsWrapper result = new OptionsWrapper();
		for (int i = 0; i < options.size(); i++) {
			Option currentOption = options.get(i);
			if (currentOption.getCorrect()) {
				result.setExpected(i + 1);
			}
		}
		String textOption1 = getTextOption(options, 1);
		result.setTextOption1(textOption1);

		String textOption2 = getTextOption(options, 2);
		result.setTextOption2(textOption2);

		String textOption3 = getTextOption(options, 3);
		result.setTextOption3(textOption3);

		String textOption4 = getTextOption(options, 4);
		result.setTextOption4(textOption4);

		return result;
	}

	private String getTextOption(List<Option> options, int optionNumber) {
		String textOption = "";
		if (optionNumber > 0 && options.size() >= optionNumber) {
			Option option = options.get(optionNumber - 1);
			textOption = option.getTextOption();
		}

		return textOption;
	}

}