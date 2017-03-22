package it4kids.service.quiz;

import it4kids.dao.indatabase.quiz.QuizDAO;
import it4kids.domain.quiz.Quiz;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class QuizService {
	private static final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

	@Autowired
	private QuizDAO dao;

	public Collection<Quiz> listAll() {
		return dao.getAll();
	}

	public Collection<Quiz> search(String query) {
		LOGGER.debug("Searching for " + query);
		return dao.searchByName(query);
	}

	public boolean delete(Long id) {
		LOGGER.debug("Deleting quiz for id: " + id);
		Quiz emp = dao.findById(id);
		if (emp != null) {
			dao.delete(emp);
			return true;
		}

		return false;
	}

	public Quiz get(Long id) {
		LOGGER.debug("Getting quiz for id: " + id);
		return dao.findById(id);

	}

	public void save(Quiz quiz) throws it4kids.service.ValidationException {
		LOGGER.debug("Saving: " + quiz);
		validate(quiz);

		dao.update(quiz);
	}

	private void validate(Quiz quiz) throws it4kids.service.ValidationException {
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(quiz.getClass())) {
			errors.add("Name is Empty");
		}

		if (quiz.getQuestions() == null) {
			errors.add("There are no questions");
		}

		if (!errors.isEmpty()) {
			throw new it4kids.service.ValidationException(errors.toArray(new String[] {}));
		}
	}

	public QuizDAO getQuizDao() {
		return dao;
	}

	public void setQuizDao(QuizDAO dao) {
		this.dao = dao;
	}

}
