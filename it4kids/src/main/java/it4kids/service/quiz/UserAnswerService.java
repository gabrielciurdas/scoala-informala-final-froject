package it4kids.service.quiz;

import it4kids.dao.BaseDAO;
import it4kids.domain.quiz.QuizAnswer;
import it4kids.service.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserAnswerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAnswerService.class);
	
	@Autowired
	@Qualifier("IMAnswerDAO")
	private BaseDAO<QuizAnswer> daoAnswer;

	public Collection<QuizAnswer> listAll() {
		return daoAnswer.getAll();
	}

	public void saveAnswer(QuizAnswer qa) throws ValidationException {

		LOGGER.debug("Saving: " + qa);
		validate(qa);
		daoAnswer.update(qa);
	}

	public void read() {

	}

	private void validate(QuizAnswer qa) throws ValidationException {
		List<String> errors = new LinkedList<>();
		if (qa.getClass() == null) {
			errors.add("No answer is Inserted !");
		}

		if (qa.getOptions() == null) {
			errors.add("There are no options for a question !");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}
}
