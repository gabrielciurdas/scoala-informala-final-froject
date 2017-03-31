package it4kids.dao.indatabase.quiz;

<<<<<<< HEAD
import java.util.Collection;
=======
import it4kids.dao.BaseDAO;
import it4kids.domain.quiz.QuizAnswer;

public interface AnswerDAO extends BaseDAO<QuizAnswer> {
>>>>>>> b1019802cd67b23e2b6ceb47cc171b46b4be3f3c

import it4kids.dao.BaseDAO;
import it4kids.domain.quiz.Option;
import it4kids.domain.quiz.QuizAnswer;
import it4kids.domain.quiz.UserAnswer;

public interface AnswerDAO extends BaseDAO<QuizAnswer> {

	Collection<QuizAnswer> searchByName(String query);
}