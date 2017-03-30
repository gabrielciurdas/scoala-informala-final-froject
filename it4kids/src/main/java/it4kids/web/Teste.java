package it4kids.web;

import it4kids.domain.quiz.Option;
import it4kids.domain.quiz.QuizEntry;
import it4kids.service.quiz.QuizService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class Teste {

	private static final int OPTION_ONE = 1;
	private static final int OPTION_TWO = 2;
	private static final int OPTION_THREE = 3;
	private static final int OPTION_FOUR = 4;

	@Autowired
	QuizService quizService;

	public static void main(String[] args) {
		OptionsWrapper qef = new OptionsWrapper();

		Option o1 = new Option();
		o1.setTextOption(qef.getTextOption1());
		o1.setCorrect(OPTION_ONE == qef.getExpected());

		Option o2 = new Option();
		o2.setTextOption(qef.getTextOption1());
		o2.setCorrect(OPTION_TWO == qef.getExpected());

		Option o3 = new Option();
		o3.setTextOption(qef.getTextOption1());
		o3.setCorrect(OPTION_THREE == qef.getExpected());

		Option o4 = new Option();
		o4.setTextOption(qef.getTextOption1());
		o4.setCorrect(OPTION_FOUR == qef.getExpected());

	}

	public ModelAndView saveQuizEntry(Long quizId, Integer expected,
			QuizEntry quizEntry, BindingResult bindingresult) {

		List<Option> options = new ArrayList<>();

		for (int i = 0; i < options.size(); i++) {
			Option current = options.get(i);
			current.setCorrect(i + 1 == expected);
		}

		ModelAndView result = new ModelAndView();
		return result;
	}

}
