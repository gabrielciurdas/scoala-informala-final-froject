package it4kids.web;

import it4kids.dao.indatabase.quiz.AnswerDAO;
import it4kids.domain.quiz.Option;
import it4kids.domain.quiz.Quiz;
import it4kids.domain.quiz.QuizAnswer;
import it4kids.domain.quiz.QuizEntry;
import it4kids.service.ValidationException;
import it4kids.service.quiz.QuizService;
import it4kids.service.quiz.UserAnswerService;

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
		QuizEntry quizEntry = new QuizEntry();
		OptionsWrapper qef = new OptionsWrapper();
		// quizEntry.setQuiz(quiz);
		result.addObject("quiz", quiz);
		result.addObject("quizEntry", quizEntry);
		result.addObject("option", qef);
		result.addObject("answers", quizAnswer);

		return result;
	}

	@RequestMapping(value = "/saveAnswer", method = RequestMethod.POST)
	public ModelAndView saveQuizAnswer(Long quizId, QuizAnswer quizAnswer, BindingResult bindingresult)
			throws ValidationException {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
				Quiz quiz = quizService.get(quizId);
				// quizEntry.setId(quizEntryId);
				// quizEntry.setQuizId(quizId);
				userService.saveAnswer(quizAnswer);

				// List<Option> options = saveOptions(quizEntryId, qef);
				// doar pt IMDAO -- start
				// quizEntry.setOptions(options);
				// quizService.saveQuizEntry(quizEntry);
				// doar pt IMDAO -- end
				updateQuizAnswer(quizAnswer, quiz);
				// quizService.save(quiz);
				// userService.saveAnswer(quizAnswer);
				result = new ModelAndView();
				result.addObject("quizId", quiz.getId());
				result.setView(new RedirectView("/answer/index"));
			} catch (ValidationException e) {
				result = new ModelAndView("/answer/addQuizAnswer");
				result.addObject("error", e.getMessage());
				result.addObject("quizAnswer", quizAnswer);
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

			result = new ModelAndView("/answer/addQuizAnswer");
			result.addObject("error", sb.toString());
			result.addObject("quizAnswer", quizAnswer);
		}
		return result;
	}

	private void updateQuizAnswer(QuizAnswer quizAnswer, Quiz quiz) {
		// TODO Auto-generated method stub
		// List<QuizAnswer> answers = quizAnswer.get;

		// for (Iterator<QuizEntry> it = questions.iterator(); it.hasNext();) {
		// QuizEntry entry = it.next();
		//
		// if (entry.getId() == quizEntry.getId()) {
		// it.remove();
		// }
		// }
		// questions.add(quizEntry);

	}

	@RequestMapping("/addQuizAnswer")
	public ModelAndView addQuizAnswer(Long quizAnswerId) {
		ModelAndView result = new ModelAndView("answer/addQuizAnswer");
		Quiz quiz = quizService.get(quizAnswerId);
		QuizAnswer quizAnswer = new QuizAnswer();
		quizAnswer.setQuizAnswerId(quizAnswerId);
		result.addObject("quiz", quiz);
		result.addObject("quizAnswer", quizAnswer);

		return result;
	}

}
