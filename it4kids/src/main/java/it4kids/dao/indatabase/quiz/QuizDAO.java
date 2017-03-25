package it4kids.dao.indatabase.quiz;

import java.util.Collection;

import it4kids.dao.BaseDAO;
import it4kids.domain.quiz.Quiz;

public interface QuizDAO extends BaseDAO<Quiz> {
	// aici cauta in baza de date fiecare quiz dupa un name
	Collection<Quiz> searchByName(String query);

}