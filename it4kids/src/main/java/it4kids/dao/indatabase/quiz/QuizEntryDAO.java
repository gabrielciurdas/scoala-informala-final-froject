package it4kids.dao.indatabase.quiz;

import it4kids.dao.BaseDAO;
import it4kids.domain.quiz.QuizEntry;

import java.util.Collection;

public interface QuizEntryDAO extends BaseDAO<QuizEntry> {
	// aici cauta in baza de date fiecare quiz dupa un name
	Collection<QuizEntry> searchByName(String query);

}

