package it4kids.dao.indatabase.quiz;

import it4kids.dao.BaseDAO;
import it4kids.domain.quiz.QuizEntry;

import java.util.Collection;

public interface QuizEntryDAO extends BaseDAO<QuizEntry> {
	
	Collection<QuizEntry> searchByName(String query);

}

