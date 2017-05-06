package it4kids.service.quiz;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import it4kids.dao.indatabase.quiz.AnswerDAO;
import it4kids.domain.quiz.QuizAnswer;

public class UserAnswerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAnswerService.class);
	@Autowired
	private AnswerDAO daoAnswer;

	public Collection<QuizAnswer> listAll() {
		return daoAnswer.getAll();
	}

	public void saveAnswer(QuizAnswer qa) throws it4kids.service.ValidationException {

		LOGGER.debug("Saving: " + qa);
		validate(qa);
		daoAnswer.update(qa);
	}

	public void read() {

	}

	private void validate(QuizAnswer qa) throws it4kids.service.ValidationException {
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(qa.getClass())) {
			errors.add("No answer is Inserted !");
		}

		if (qa.getOptions() == null) {
			errors.add("There are no options for a question !");
		}

		if (!errors.isEmpty()) {
			throw new it4kids.service.ValidationException(errors.toArray(new String[] {}));
		}
	}

	public void setAnswerDAO(AnswerDAO daoAnswer) {
		this.daoAnswer = daoAnswer;
	}

	public AnswerDAO getAnswerDAO() {
		return daoAnswer;
	}

}