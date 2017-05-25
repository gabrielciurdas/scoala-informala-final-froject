package it4kids.service.quiz;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it4kids.dao.indatabase.quiz.OptionDAO;
import it4kids.dao.indatabase.quiz.QuizDAO;
import it4kids.dao.indatabase.quiz.QuizEntryDAO;
import it4kids.domain.quiz.Option;
import it4kids.domain.quiz.Quiz;
import it4kids.domain.quiz.QuizEntry;
import it4kids.service.ValidationException;

@Service
public class QuizService {
	private static final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

	@Autowired
	private QuizDAO daoQuiz;
	@Autowired
	private QuizEntryDAO daoQuizEntry;
	@Autowired
	private OptionDAO daoOption;

	public Collection<Quiz> listAll() {
		return daoQuiz.getAll();
	}

	public boolean delete(Long id) {
		LOGGER.debug("Deleting quiz for id: " + id);
		Quiz emp = daoQuiz.findById(id);
		if (emp != null) {
			daoQuiz.delete(emp);
			return true;
		}
		return false;
	}

	public boolean deleteQuizEntry(Long id) {
		LOGGER.debug("Deleting quiz for id: " + id);
		QuizEntry emp = daoQuizEntry.findById(id);
		if (emp != null) {
			daoQuizEntry.delete(emp);
			return true;
		}
		return false;
	}

	public boolean deleteOption(Long id) {
		LOGGER.debug("Deleting quiz for id: " + id);
		Option emp = daoOption.findById(id);
		if (emp != null) {
			daoOption.delete(emp);
			return true;
		}

		return false;
	}

	public Quiz get(Long id) {
		LOGGER.debug("Getting quiz for id: " + id);
		return daoQuiz.findById(id);

	}

	public QuizEntry getQuizEntry(Long id) {
		LOGGER.debug("Getting quiz for id: " + id);
		return daoQuizEntry.findById(id);

	}

	public Option getOption(Long id) {
		LOGGER.debug("Getting quiz for id: " + id);
		return daoOption.findById(id);

	}

	public void save(@Valid Quiz quiz)
			throws ValidationException {
		LOGGER.debug("Saving: " + quiz);
		validate(quiz);

		daoQuiz.update(quiz);
	}

	public void saveQuizEntry(QuizEntry entry) throws ValidationException {
		LOGGER.debug("Saving: " + entry);
		validateQuizEntry(entry);

		daoQuizEntry.update(entry);
	}

	public void saveOption(Option option) throws ValidationException {
		LOGGER.debug("Saving: " + option);
		validateOption(option);

		daoOption.update(option);
	}

	private void validate(@Valid Quiz quiz)
			throws it4kids.service.ValidationException {
		List<String> errors = new LinkedList<String>();
		if (quiz.getName().isEmpty()) {
			errors.add("Name is Empty");
		}

		if (quiz.getQuestions() == null) {
			errors.add("There are no questions");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	private void validateQuizEntry(QuizEntry list) throws ValidationException {
		List<String> errors = new LinkedList<String>();
		if (list.getText().isEmpty()) {
			errors.add("There is no question");
		}

		if (list.getOptions() == null) {
			errors.add("There are no answers");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	private void validateOption(Option option) throws ValidationException {
		List<String> errors = new LinkedList<String>();
		if (option.getTextOption().isEmpty()) {
			errors.add("Name is Empty");
		}

		if (option.getTextOption() == null) {
			errors.add("There are no questions");
		}

		// if (option.getCorrect() == null) {
		// errors.add("There are no answears");
		// }

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	public QuizDAO getQuizDao() {
		return daoQuiz;
	}

	public void setQuizDao(QuizDAO dao) {
		this.daoQuiz = dao;
	}

	public QuizEntryDAO getQuizEntryDao() {
		return daoQuizEntry;
	}

	public void setQuizEntryDao(QuizEntryDAO dao) {
		this.daoQuizEntry = dao;
	}

	public OptionDAO getOptionDao() {
		return daoOption;
	}

	public void setOptionDao(OptionDAO dao) {
		this.daoOption = dao;
	}

}
