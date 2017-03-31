package it4kids.dao.indatabase.quiz;

import java.util.Collection;

import it4kids.dao.BaseDAO;
import it4kids.domain.quiz.Option;
import it4kids.domain.quiz.QuizAnswer;
import it4kids.domain.quiz.UserAnswer;

public interface AnswerDAO extends BaseDAO<QuizAnswer> {

	Collection<QuizAnswer> searchByName(String query);
}