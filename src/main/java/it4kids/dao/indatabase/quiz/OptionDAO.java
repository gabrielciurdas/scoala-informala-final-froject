package it4kids.dao.indatabase.quiz;

import it4kids.dao.BaseDAO;
import it4kids.domain.quiz.Option;

import java.util.Collection;

public interface OptionDAO extends BaseDAO<Option> {
	// aici cauta in baza de date fiecare quiz dupa un name
	Collection<Option> searchByName(String query);

}

