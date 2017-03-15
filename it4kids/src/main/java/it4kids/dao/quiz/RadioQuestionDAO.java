package it4kids.dao.quiz;

import java.util.Collection;

import it4kids.domain.quiz.RadioQuestion;

public interface RadioQuestionDAO extends BaseDAO<RadioQuestion> {
	// aici m-am gandit ca intrebarile sa pot fi cautate dupa un ID , daca aveti
	// o metoda mai buna sa ziceti.
	Collection<RadioQuestion> searchByID(String query);

}
