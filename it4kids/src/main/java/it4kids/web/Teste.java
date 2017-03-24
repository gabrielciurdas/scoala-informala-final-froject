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

	@Autowired
	QuizService quizService;

	public static void main(String[] args) {

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
