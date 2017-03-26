package it4kids.service.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it4kids.dao.indatabase.quiz.AnswerDAO;

public class UserAnswerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAnswerService.class);
	@Autowired
	private AnswerDAO daoAnswer;

	public void list() {

	}

	public void getAnswersFromDB() {

	}

	public void setDaoAnswer(AnswerDAO daoAnswer) {
		this.daoAnswer = daoAnswer;
	}

	public AnswerDAO getDaoAnswer() {
		return daoAnswer;
	}

}
