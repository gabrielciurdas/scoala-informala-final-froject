package it4kids.dao.quiz;

import java.util.Collection;

import it4kids.domain.quiz.QuizEntry;

public interface QuizEntryDAO extends BaseDAO<QuizEntry> {
	// aici m-am gandit ca intrebarile sa pot fi cautate dupa un ID , daca aveti
	// o metoda mai buna sa ziceti.
	Collection<QuizEntry> searchByID(String query);

}
