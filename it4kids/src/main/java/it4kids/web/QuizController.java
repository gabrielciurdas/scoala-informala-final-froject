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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("")
public class QuizController {
	private long id;

	@Autowired
	private QuizService quizService;
	@Autowired
	private QuizEntryService quizEntryService;
	@Autowired
	private OptionService optionService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("quizz/index");

		Collection<Quiz> quizes = quizService.listAll();
		result.addObject("quiz", new Quiz());
		result.addObject("quizList", quizes);

		return result;
<<<<<<< HEAD

	}

	@RequestMapping("add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("quiz/add");
		result.addObject("quiz", new Quiz());
		return result;
	}
	
//	@RequestMapping("edit")
//	public ModelAndView edit(long id) {
//		ModelAndView result = new ModelAndView("quiz/edit");
//		result.addObject("quiz", new Quiz());
//		return result;
//	}

	@RequestMapping("save")
	public ModelAndView saveQuiz(Quiz quiz, BindingResult bindingresult) {
=======
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView saveQuiz(Quiz quiz, BindingResult bindingresult)
			throws ValidationException {
>>>>>>> origin/Catalin
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {
				id++;
				quiz.setId(id);
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

		// Collection<QuizEntry> quizEntryes = quizEntryService.listAll();
		result.addObject("quizEntry", new QuizEntry());
		result.addObject("quiz", quizService.get(id));
		// result.addObject("quizEntryList", quizEntryes);

		return result;
	}

	@RequestMapping(value = "/edit/add", method = RequestMethod.POST)
	public ModelAndView saveQuizEntry(Long id, QuizEntry quizEntry,
			Option option,
			BindingResult bindingresult) throws ValidationException {
		ModelAndView result = null;
		if (!bindingresult.hasErrors()) {

			try {

				quizEntryService.save(quizEntry);
				optionService.save(option);
				Quiz quiz = quizService.get(id);
				quiz.getQuestions().add(quizEntry);
				quizService.save(quiz);
				result = new ModelAndView();
				result.addObject("id", quiz.getId());
				// result.addObject("quizEntry", new QuizEntry());
				// result.addObject("quiz", quiz);
				result.setView(new RedirectView("/edit"));
			} catch (ValidationException e) {
				result = new ModelAndView("quizz/edit");
				result.addObject("error", e.getMessage());
				result.addObject("quizEntry", quizEntry);
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

			result = new ModelAndView("quizz/edit");
			result.addObject("error", sb.toString());
			result.addObject("quizEntry", quizEntry);
			result.addObject("option", option);
		}
		return result;
	}

	//
	// public String add(@ModelAttribute("user") Quiz quiz) throws
	// ValidationException {
	//
	// if (null != quiz && null != quiz.getName()
	// && !quiz.getName().isEmpty()) {
	//
	// synchronized (quizService) {
	// quizService.save(quiz);
	// }
	//
	// }
	//
	// return "redirect:index.html";
	// }

	// @RequestMapping("")
	// public ModelAndView list() {
	// ModelAndView result = new ModelAndView("quiz/list");
	// Collection<Quiz> quizes = quizService.listAll();
	// result.addObject("quizList", quizes);
	//
	// return result;
	//
	// }
	//
	// @RequestMapping("/add")
	// public ModelAndView add() {
	// ModelAndView result = new ModelAndView("quiz/add");
	// result.addObject("quiz", new Quiz());
	// return result;
	// }
	//
	// @RequestMapping("/save")
	// public ModelAndView saveQuiz(Quiz quiz, BindingResult bindingresult) {
	// ModelAndView result = null;
	// if (!bindingresult.hasErrors()) {
	//
	// try {
	// quizService.save(quiz);
	// result = new ModelAndView();
	// result.setView(new RedirectView("/quiz"));
	// } catch (ValidationException e) {
	// // TODO Auto-generated catch block
	// result = new ModelAndView("quiz/add");
	// result.addObject("error", e.getMessage());
	// result.addObject("quiz", quiz);
	// }
	// } else {
	// List<FieldError> errors = bindingresult.getFieldErrors();
	// StringBuilder sb = new StringBuilder();
	// for (FieldError fieldError : errors) {
	// sb.append(fieldError.getField());
	// sb.append("-");
	// sb.append(fieldError.getCode());
	// sb.append("<br>");
	// }
	//
	// result = new ModelAndView("quiz/add");
	// result.addObject("error", sb.toString());
	// result.addObject("quiz", quiz);
	// }
	// return result;
}
//
// @RequestMapping("/delete")
// public ModelAndView delete(Long id) {
// quizService.delete(id);
// ModelAndView result = new ModelAndView();
// RedirectView redirect = new RedirectView("");
// result.setView(redirect);
// return result;
// }
//
// @RequestMapping("/edit")
// public ModelAndView edit(Long id) {
// ModelAndView result = new ModelAndView("quiz/add");
// Quiz quiz = quizService.get(id);
// if (quiz != null) {
// result.addObject("quiz", quiz);
// } else {
// result = new ModelAndView();
// RedirectView redirect = new RedirectView("");
// result.setView(redirect);
// }
// return result;
// }
//
// @RequestMapping(value = "")
// public ModelAndView listQuestions() {
// ModelAndView result = new ModelAndView("quiz/list");
// Collection<QuizEntry> quizEntry = quizEntryService.listAll();
//
// result.addObject("quizEntryList", quizEntry);
// return result;
// }
//
// @RequestMapping(value = "/add")
// public ModelAndView addQuestion(QuizEntry quizEntry) {
// ModelAndView result = new ModelAndView("quiz/addQuizEntry");
// result.addObject("quizEntry", new QuizEntry());
// return result;
// }
//
// @RequestMapping("/save")
// public ModelAndView saveQuestion(QuizEntry quizEntry,
// BindingResult bindingresult) {
// ModelAndView result = null;
// if (!bindingresult.hasErrors()) {
//
// try {
// quizEntryService.save(quizEntry);
// result = new ModelAndView();
// result.setView(new RedirectView("/quiz"));
// } catch (ValidationException e) {
// // TODO Auto-generated catch block
// result = new ModelAndView("quiz/add");
// result.addObject("error", e.getMessage());
// result.addObject("quizEntry", quizEntry);
// }
// } else {
// List<FieldError> errors = bindingresult.getFieldErrors();
// StringBuilder sb = new StringBuilder();
// for (FieldError fieldError : errors) {
// sb.append(fieldError.getField());
// sb.append("-");
// sb.append(fieldError.getCode());
// sb.append("<br>");
// }
//
// result = new ModelAndView("quiz/add");
// result.addObject("error", sb.toString());
// result.addObject("quizEntry", quizEntry);
// }
// return result;
// }
//
// // @RequestMapping(value = "/questions/saveQuestionList?id={quiz.id!''}")
// // public ModelAndView saveQuestionList(
// // @RequestParam(value = "quiz.id!''", required = false) Long id)
// // throws ValidationException {
// // ModelAndView result = null;
// //
// // quizEntryService.listAll();
// // result = new ModelAndView();
// // result.setView(new RedirectView("/quiz"));
// // return result;
// // }
//
// @RequestMapping("")
// public ModelAndView listOptions() {
// ModelAndView result = new ModelAndView("quiz/list");
// Collection<Option> option = optionService.listAll();
// result.addObject("optionList", option);
// return result;
// }
//
// @RequestMapping("/add")
// public ModelAndView addOption() {
// ModelAndView result = new ModelAndView("quiz/add");
// result.addObject("option", new Option());
// return result;
// }
//
// @RequestMapping("/save")
// public ModelAndView saveOption(Option option, BindingResult
// bindingresult) {
// ModelAndView result = null;
// if (!bindingresult.hasErrors()) {
//
// try {
// optionService.save(option);
// result = new ModelAndView();
// result.setView(new RedirectView("/quiz"));
// } catch (ValidationException e) {
// // TODO Auto-generated catch block
// result = new ModelAndView("quiz/add");
// result.addObject("error", e.getMessage());
// result.addObject("option", option);
// }
// } else {
// List<FieldError> errors = bindingresult.getFieldErrors();
// StringBuilder sb = new StringBuilder();
// for (FieldError fieldError : errors) {
// sb.append(fieldError.getField());
// sb.append("-");
// sb.append(fieldError.getCode());
// sb.append("<br>");
// }
//
// result = new ModelAndView("quiz/add");
// result.addObject("error", sb.toString());
// result.addObject("option", option);
// }
// return result;
// }
//
// // @RequestMapping("questions/options/saveOptionList")
// // public ModelAndView saveOptionList() throws ValidationException {
// // ModelAndView result = null;
// //
// // optionService.listAll();
// // result = new ModelAndView();
// // result.setView(new RedirectView("/quiz/questions"));
// // return result;
// // }
