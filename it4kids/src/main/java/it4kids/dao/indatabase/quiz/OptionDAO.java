package it4kids.dao.indatabase.quiz;

import it4kids.dao.BaseDAO;
import it4kids.domain.quiz.Option;

import java.util.Collection;

public interface OptionDAO extends BaseDAO<Option> {
	
	Collection<Option> searchByName(String query);

}

