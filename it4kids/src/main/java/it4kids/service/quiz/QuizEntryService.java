package it4kids.service.quiz;

import it4kids.dao.indatabase.quiz.QuizEntryDAO;
import it4kids.domain.quiz.QuizEntry;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class QuizEntryService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuizService.class);

	@Autowired
	private QuizEntryDAO dao;

	public Collection<QuizEntry> listAll() {
		return dao.getAll();
	}

	public Collection<QuizEntry> search(String query) {
		LOGGER.debug("Searching for " + query);
		return dao.searchByName(query);
	}

	public boolean delete(Long id) {
		LOGGER.debug("Deleting quiz for id: " + id);
		QuizEntry emp = dao.findById(id);
		if (emp != null) {
			dao.delete(emp);
			return true;
		}

		return false;
	}

	public QuizEntry get(Long id) {
		LOGGER.debug("Getting quiz for id: " + id);
		return dao.findById(id);

	}

	public void save(QuizEntry quizEntry)
			throws it4kids.service.ValidationException {
		LOGGER.debug("Saving: " + quizEntry);
		validate(quizEntry);

		dao.update(quizEntry);
	}

	private void validate(QuizEntry quizEntry)
			throws it4kids.service.ValidationException {
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(quizEntry.getClass())) {
			errors.add("There is no question");
		}


		if (quizEntry.getOption() == null) {
			errors.add("There are no answears");
		}

		if (!errors.isEmpty()) {
			throw new it4kids.service.ValidationException(
					errors.toArray(new String[] {}));
		}
	}

	public QuizEntryDAO getQuizEntryDao() {
		return dao;
	}

	public void setQuizEntryDao(QuizEntryDAO dao) {
		this.dao = dao;
	}


}
